package br.alexandregpereira.offbalance.ui.foundation

import androidx.compose.ui.graphics.Color

internal object OffbalanceColors {
    
    // Primary Colors
    val Primary = Color(0xFF5809DF) // Indigo

    // Background Colors
    val Background = Color(0xFF1A1A2E) // Main background gradient start
    val SurfaceDark = Color(0xFF111827) // Phone screens, content areas
    val SurfaceMedium = Color(0xFF1F2937) // Cards, input fields
    val SurfaceLight = Color(0xFF374151) // Borders, dividers
    
    // Text Colors
    val TextPrimary = Color(0xFFE5E7EB) // Main text content
    val TextSecondary = Color(0xFF9CA3AF) // Supporting text, metadata
    val TextMuted = Color(0xFF6B7280) // Disabled text, placeholders
    val TextWhite = Color(0xFFFFFFFF) // Primary text on gradients
    
    // Status Colors
    val Success = Color(0xFF018157) // Positive values, income
    val Error = Color(0xFFBB1717) // Negative amounts, errors
    val Warning = Color(0xFFF59E0B) // Warnings, pending states
    
    // Border Colors
    val Border = Color(0xFF374151) // Default borders
    val BorderFocus = Color(0xFF6366F1) // Focus state borders
    val BorderTransparent = Color(0x1AFFFFFF) // 10% white transparency
}