package br.alexandregpereira.offbalance.ui.foundation

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes

// Shape system based on design system
val OffbalanceShapes = Shapes(
    small = RoundedCornerShape(OffbalanceRadius.sm),
    medium = RoundedCornerShape(OffbalanceRadius.md),
    large = RoundedCornerShape(OffbalanceRadius.lg)
)

// Additional custom shapes
object OffbalanceCustomShapes {
    val button = RoundedCornerShape(OffbalanceRadius.md) // 8dp
    val card = RoundedCornerShape(OffbalanceRadius.lg) // 12dp
    val section = RoundedCornerShape(OffbalanceRadius.xl) // 16dp
    val phoneFrame = RoundedCornerShape(OffbalanceRadius.xxl) // 25dp
    val circular = RoundedCornerShape(OffbalanceRadius.full) // 50dp (circular)
    
    // Specific component shapes
    val inputField = RoundedCornerShape(OffbalanceRadius.md) // 8dp
    val dialog = RoundedCornerShape(OffbalanceRadius.xl) // 16dp
    val bottomSheet = RoundedCornerShape(
        topStart = OffbalanceRadius.xl,
        topEnd = OffbalanceRadius.xl
    )
}