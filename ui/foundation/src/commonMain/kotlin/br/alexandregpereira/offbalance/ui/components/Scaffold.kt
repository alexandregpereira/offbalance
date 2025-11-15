package br.alexandregpereira.offbalance.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import br.alexandregpereira.offbalance.ui.foundation.OffbalanceTheme

@Composable
fun Scaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    backgroundColor: Color = OffbalanceTheme.colors.background,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize().background(color = backgroundColor)
    ) {
        topBar()
        content()
        bottomBar()
    }
}