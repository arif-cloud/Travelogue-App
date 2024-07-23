package com.example.travelogueapp.presentation.generate.component.loading

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.travelogueapp.R
import com.example.travelogueapp.presentation.generate.component.FeatureInfo

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier,
    generatedFeatures: List<String>
) {
    val loadingProgress = remember { Animatable(0f) }
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.lottie_animation))
    Column(modifier = modifier.padding(horizontal = 16.dp, vertical = 24.dp)) {
        LottieAnimation(
            modifier = Modifier.weight(1f),
            composition = composition
        )
        AnimatedProgress(
            modifier = Modifier.size(60.dp),
            animateFloat = loadingProgress
        )
        Spacer(modifier = Modifier.height(20.dp))
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            generatedFeatures.forEachIndexed { index, text ->
                GeneratedFeatureItem(
                    modifier = Modifier.fillMaxWidth(),
                    featureText = text,
                    isCreated = loadingProgress.value >= (index + 1) * (100f / generatedFeatures.size.toLong())
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PrevLoadingScreen() {
    // LoadingScreen()
}