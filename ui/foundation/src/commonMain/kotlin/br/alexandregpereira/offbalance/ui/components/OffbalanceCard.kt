package br.alexandregpereira.offbalance.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import br.alexandregpereira.offbalance.ui.foundation.OffbalanceColors
import br.alexandregpereira.offbalance.ui.foundation.OffbalanceGradients
import br.alexandregpereira.offbalance.ui.foundation.OffbalanceSize
import br.alexandregpereira.offbalance.ui.foundation.offbalance

// Card variants matching the design system
enum class OffbalanceCardStyle {
    PRIMARY,    // Primary gradient card
    SECONDARY,  // Surface medium with border
    SUCCESS,    // Success gradient card
    GLASS       // Glassmorphism card
}

@Composable
fun OffbalanceCard(
    modifier: Modifier = Modifier,
    style: OffbalanceCardStyle = OffbalanceCardStyle.SECONDARY,
    shape: Shape = MaterialTheme.offbalance.customShapes.card,
    onClick: (() -> Unit)? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable () -> Unit
) {
    val backgroundBrush = when (style) {
        OffbalanceCardStyle.PRIMARY -> OffbalanceGradients.Primary
        OffbalanceCardStyle.SUCCESS -> OffbalanceGradients.Success
        OffbalanceCardStyle.GLASS -> OffbalanceGradients.Glass
        OffbalanceCardStyle.SECONDARY -> null
    }
    
    val backgroundColor = when (style) {
        OffbalanceCardStyle.SECONDARY -> OffbalanceColors.SurfaceMedium
        OffbalanceCardStyle.GLASS -> Color.Transparent
        else -> Color.Transparent // Gradient cards use Brush
    }
    
    val borderColor = when (style) {
        OffbalanceCardStyle.SECONDARY -> OffbalanceColors.Border
        OffbalanceCardStyle.GLASS -> OffbalanceColors.BorderTransparent
        else -> Color.Transparent
    }
    
    val borderWidth = when (style) {
        OffbalanceCardStyle.SECONDARY, OffbalanceCardStyle.GLASS -> 1.dp
        else -> 0.dp
    }
    
    Box(
        modifier = modifier
            .defaultMinSize(minHeight = OffbalanceSize.cardMinHeight)
            .clip(shape)
            .run {
                if (backgroundBrush != null) {
                    background(backgroundBrush, shape)
                } else {
                    background(backgroundColor, shape)
                }
            }
            .run {
                if (borderWidth > 0.dp) {
                    border(borderWidth, borderColor, shape)
                } else this
            }
            .run {
                if (onClick != null) {
                    clickable(
                        interactionSource = interactionSource,
                        indication = ripple(),
                        onClick = onClick
                    )
                } else this
            }
            .padding(OffbalanceSize.cardPadding)
    ) {
        content()
    }
}

// Specialized card for financial data display
@Composable
fun FinancialCard(
    modifier: Modifier = Modifier,
    style: OffbalanceCardStyle = OffbalanceCardStyle.PRIMARY,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    OffbalanceCard(
        modifier = modifier,
        style = style,
        onClick = onClick,
        content = content
    )
}