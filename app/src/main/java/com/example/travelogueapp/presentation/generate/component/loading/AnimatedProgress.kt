package com.example.travelogueapp.presentation.generate.component.loading

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.travelogueapp.ui.theme.Typography

@Composable
fun AnimatedProgress(
    modifier: Modifier = Modifier,
    animateFloat: Animatable<Float, AnimationVector1D>,
    progressIndicatorColor: Color = MaterialTheme.colorScheme.primary
) {
    val stroke = with(LocalDensity.current) {
        Stroke(width = 6.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
    }
    LaunchedEffect(Unit) {
        animateFloat.animateTo(
            targetValue = 100f,
            animationSpec = tween(durationMillis = 8000, easing = FastOutSlowInEasing)
        )
    }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Canvas(modifier = modifier) {
            val startAngle = 270f
            val sweep: Float = (animateFloat.value / 100) * 360f
            val diameterOffset = stroke.width / 2
            drawArc(
                color = progressIndicatorColor,
                startAngle = startAngle,
                sweepAngle = sweep,
                useCenter = false,
                topLeft = Offset(diameterOffset, diameterOffset),
                size = Size(size.width - 2 * diameterOffset, size.height - 2 * diameterOffset),
                style = stroke
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = "${animateFloat.value.toInt()}%",
            style = Typography.titleLarge,
            color = progressIndicatorColor
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PrevAnimatedProgress() {
    val loadingProgress = remember { Animatable(0f) }
    AnimatedProgress(modifier = Modifier.size(40.dp), animateFloat = loadingProgress)
}