package br.alexandregpereira.offbalance.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import br.alexandregpereira.offbalance.ui.foundation.OffbalanceColors

@Composable
fun OffbalanceScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    backgroundColor: Color = Color.Transparent,
    contentColor: Color = OffbalanceColors.TextPrimary,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = topBar,
        bottomBar = bottomBar,
        floatingActionButton = floatingActionButton,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        content = content
    )
}