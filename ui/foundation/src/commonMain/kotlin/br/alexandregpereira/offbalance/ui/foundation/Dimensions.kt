package br.alexandregpereira.offbalance.ui.foundation

import androidx.compose.ui.unit.dp

// Spacing scale based on design system
object OffbalanceSpacing {
    val xs = 4.dp
    val sm = 8.dp
    val md = 12.dp
    val lg = 16.dp
    val xl = 20.dp
    val xxl = 24.dp
    val xxxl = 32.dp
    
    // Additional spacing for specific use cases
    val component = 16.dp // Standard component spacing
    val section = 32.dp // Section spacing
    val screen = 20.dp // Screen edge padding
}

// Border radius scale
object OffbalanceRadius {
    val sm = 4.dp
    val md = 8.dp // Default radius
    val lg = 12.dp // Cards
    val xl = 16.dp // Sections
    val xxl = 25.dp // Phone mockup
    val full = 50.dp // Circular elements
}

// Elevation/Shadow values
object OffbalanceElevation {
    val sm = 2.dp
    val md = 4.dp
    val lg = 8.dp
    val xl = 16.dp
}

// Component dimensions
object OffbalanceSize {
    // Button dimensions
    val buttonHeight = 48.dp
    val buttonMinWidth = 64.dp
    val buttonPaddingHorizontal = OffbalanceSpacing.xxl
    val buttonPaddingVertical = OffbalanceSpacing.md
    
    // Card dimensions
    val cardMinHeight = 80.dp
    val cardPadding = OffbalanceSpacing.xl
    
    // Input field dimensions
    val inputHeight = 48.dp
    val inputPadding = OffbalanceSpacing.md
    
    // Icon dimensions
    val iconSm = 16.dp
    val iconMd = 24.dp
    val iconLg = 32.dp
    val iconXl = 48.dp
    
    // Touch target (minimum 44dp for accessibility)
    val minTouchTarget = 44.dp
}