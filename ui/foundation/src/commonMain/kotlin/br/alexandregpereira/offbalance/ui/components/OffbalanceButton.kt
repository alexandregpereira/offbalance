package br.alexandregpereira.offbalance.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import br.alexandregpereira.offbalance.ui.foundation.OffbalanceColors
import br.alexandregpereira.offbalance.ui.foundation.OffbalanceGradients
import br.alexandregpereira.offbalance.ui.foundation.OffbalanceSize
import br.alexandregpereira.offbalance.ui.foundation.offbalance

// Button variants matching the design system
enum class OffbalanceButtonStyle {
    PRIMARY,    // Primary gradient button
    SECONDARY,  // Outlined button with primary border
    DANGER,     // Error gradient button
    TEXT        // Text-only button
}

@Composable
fun OffbalanceButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: OffbalanceButtonStyle = OffbalanceButtonStyle.PRIMARY,
    enabled: Boolean = true,
    shape: Shape = MaterialTheme.offbalance.customShapes.button,
    contentPadding: PaddingValues = PaddingValues(
        horizontal = OffbalanceSize.buttonPaddingHorizontal,
        vertical = OffbalanceSize.buttonPaddingVertical
    ),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable () -> Unit
) {
    val backgroundBrush = when (style) {
        OffbalanceButtonStyle.PRIMARY -> OffbalanceGradients.Primary
        OffbalanceButtonStyle.DANGER -> OffbalanceGradients.Error
        OffbalanceButtonStyle.SECONDARY, OffbalanceButtonStyle.TEXT -> null
    }
    
    val backgroundColor = when (style) {
        OffbalanceButtonStyle.SECONDARY -> OffbalanceColors.SurfaceMedium
        OffbalanceButtonStyle.TEXT -> Color.Transparent
        else -> Color.Transparent // Gradient buttons use Brush
    }
    
    val borderColor = when (style) {
        OffbalanceButtonStyle.SECONDARY -> OffbalanceColors.Primary
        else -> Color.Transparent
    }
    
    val borderWidth = if (style == OffbalanceButtonStyle.SECONDARY) 2.dp else 0.dp
    
    Box(
        modifier = modifier
            .defaultMinSize(
                minWidth = OffbalanceSize.buttonMinWidth,
                minHeight = OffbalanceSize.buttonHeight
            )
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
            .clickable(
                interactionSource = interactionSource,
                indication = ripple(),
                enabled = enabled,
                role = Role.Button,
                onClick = onClick
            )
            .padding(contentPadding),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}