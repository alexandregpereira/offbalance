package br.alexandregpereira.offbalance.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.alexandregpereira.offbalance.ui.foundation.OffbalanceGradients

// Background gradient component for consistent app background
@Composable
fun BackgroundGradient(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(OffbalanceGradients.Background)
    ) {
        content()
    }
}