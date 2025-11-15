package br.alexandregpereira.offbalance.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun VerticalSpace(height: Dp) {
    Box(Modifier.height(height))
}

@Composable
fun HorizontalSpace(width: Dp) {
    Box(Modifier.width(width))
}
