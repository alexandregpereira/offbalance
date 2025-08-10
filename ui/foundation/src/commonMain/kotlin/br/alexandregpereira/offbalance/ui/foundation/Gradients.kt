package br.alexandregpereira.offbalance.ui.foundation

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// Gradient utilities based on design system
object OffbalanceGradients {
    
    // Primary gradient (135deg, #4f46e5, #7c3aed)
    val Primary = Brush.linearGradient(
        colors = listOf(
            OffbalanceColors.PrimaryStart,
            OffbalanceColors.PrimaryEnd
        )
    )
    
    // Success gradient (135deg, #059669, #10b981)
    val Success = Brush.linearGradient(
        colors = listOf(
            OffbalanceColors.SuccessStart,
            OffbalanceColors.SuccessEnd
        )
    )
    
    // Error gradient (135deg, #dc2626, #ef4444)
    val Error = Brush.linearGradient(
        colors = listOf(
            OffbalanceColors.ErrorStart,
            OffbalanceColors.ErrorEnd
        )
    )
    
    // Main background gradient (135deg, #1a1a2e 0%, #16213e 50%, #0f3460 100%)
    val Background = Brush.linearGradient(
        colors = listOf(
            OffbalanceColors.BackgroundStart,
            OffbalanceColors.BackgroundMid,
            OffbalanceColors.BackgroundEnd
        )
    )
    
    // Glassmorphism overlay
    val Glass = Brush.linearGradient(
        colors = listOf(
            Color(0x0DFFFFFF), // 5% white
            Color(0x1AFFFFFF)  // 10% white
        )
    )
    
    // Subtle overlay for depth
    val Overlay = Brush.verticalGradient(
        colors = listOf(
            Color(0x00000000), // Transparent
            Color(0x1A000000)  // 10% black
        )
    )
    
    // Card hover gradient effect
    val CardHover = Brush.linearGradient(
        colors = listOf(
            Color(0x0D6366F1), // 5% primary
            Color(0x1A6366F1)  // 10% primary
        )
    )
}