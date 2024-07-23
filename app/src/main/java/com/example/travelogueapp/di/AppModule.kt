package com.example.travelogueapp.di

import android.content.Context
import androidx.room.Room
import com.example.travelogueapp.common.Constants
import com.example.travelogueapp.data.local.TravelGuideDao
import com.example.travelogueapp.data.local.TravelGuideDatabase
import com.example.travelogueapp.data.local.repository.TravelGuideDatabaseRepositoryImpl
import com.example.travelogueapp.data.remote.PhotoApi
import com.example.travelogueapp.data.remote.repository.TravelGuideRepositoryImpl
import com.example.travelogueapp.domain.repository.TravelGuideDatabaseRepository
import com.example.travelogueapp.domain.repository.TravelGuideRepository
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.generationConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGenerativeModel() = GenerativeModel(
        modelName = "gemini-1.5-pro",
        apiKey = Constants.GEMINI_API_KEY,
        generationConfig = generationConfig {
            responseMimeType = "application/json"
        }
    )

    @Provides
    @Singleton
    fun providePhotoApi(): PhotoApi {
        return Retrofit.Builder()
            .baseUrl(Constants.PHOTO_API_BASE_URL)
            .client(OkHttpClient().newBuilder().addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PhotoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTravelGuideDatabase(@ApplicationContext context: Context) : TravelGuideDatabase {
        return Room.databaseBuilder(context, TravelGuideDatabase::class.java, "travel_guides").allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    fun provideTravelGuideRepository(generativeModel: GenerativeModel, photoApi: PhotoApi): TravelGuideRepository {
        return TravelGuideRepositoryImpl(generativeModel, photoApi)
    }

    @Provides
    @Singleton
    fun provideTravelGuideDatabaseRepository(travelGuideDatabase: TravelGuideDatabase): TravelGuideDatabaseRepository {
        return TravelGuideDatabaseRepositoryImpl(travelGuideDatabase.travelGuideDao())
    }

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

}