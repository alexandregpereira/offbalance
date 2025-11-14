package br.alexandregpereira.offbalance.ui.components

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import br.alexandregpereira.offbalance.ui.foundation.OffbalanceTheme

@Composable
fun OffbalanceText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = OffbalanceTheme.colors.textPrimary,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    style: TextStyle = OffbalanceTheme.typography.body2,
    textDecoration: TextDecoration? = null
) {
    BasicText(
        text = text,
        modifier = modifier,
        style = style.merge(
            textAlign = textAlign ?: TextAlign.Unspecified,
            lineHeight = lineHeight,
            textDecoration = textDecoration,
        ),
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        color = { color }
    )
}