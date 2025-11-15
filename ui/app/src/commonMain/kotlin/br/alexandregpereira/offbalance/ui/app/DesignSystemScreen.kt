package br.alexandregpereira.offbalance.ui.app

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.alexandregpereira.offbalance.ui.components.Button
import br.alexandregpereira.offbalance.ui.components.Card
import br.alexandregpereira.offbalance.ui.components.FinancialCard
import br.alexandregpereira.offbalance.ui.components.MoneyTextField
import br.alexandregpereira.offbalance.ui.components.OffbalanceButtonStyle
import br.alexandregpereira.offbalance.ui.components.OffbalanceCardStyle
import br.alexandregpereira.offbalance.ui.components.Scaffold
import br.alexandregpereira.offbalance.ui.components.Text
import br.alexandregpereira.offbalance.ui.components.TextField
import br.alexandregpereira.offbalance.ui.foundation.OffbalanceTheme

@Composable
fun DesignSystemScreen() {
    Scaffold(
        backgroundColor = OffbalanceTheme.colors.background,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(OffbalanceTheme.spacing.lg)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.lg)
        ) {
            // Header
            Text(
                text = "💰 Offbalance",
                style = OffbalanceTheme.typography.h1,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "Financial Design System Demo",
                style = OffbalanceTheme.typography.body1.copy(
                    color = OffbalanceTheme.colors.textSecondary
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(OffbalanceTheme.spacing.lg))

            // Main Balance Card
            FinancialCard(
                style = OffbalanceCardStyle.PRIMARY,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "$12,847.50",
                        style = OffbalanceTheme.typography.moneyLarge
                    )
                    Text(
                        text = "Total Balance",
                        style = OffbalanceTheme.typography.body1,
                        color = OffbalanceTheme.colors.textWhite.copy(alpha = 0.9f)
                    )
                }
            }

            // Account Cards Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.md)
            ) {
                Card(
                    style = OffbalanceCardStyle.SECONDARY,
                    modifier = Modifier.weight(1f)
                ) {
                    Column {
                        Text(
                            text = "🏦",
                            style = OffbalanceTheme.typography.h4
                        )
                        Text(
                            text = "Main Checking",
                            style = OffbalanceTheme.typography.h6
                        )
                        Text(
                            text = "$8,247.50",
                            style = OffbalanceTheme.typography.moneySmall
                        )
                    }
                }

                Card(
                    style = OffbalanceCardStyle.SUCCESS,
                    modifier = Modifier.weight(1f)
                ) {
                    Column {
                        Text(
                            text = "💰",
                            style = OffbalanceTheme.typography.h4,
                            color = OffbalanceTheme.colors.textWhite,
                        )
                        Text(
                            text = "Savings",
                            style = OffbalanceTheme.typography.h6.copy(
                                color = OffbalanceTheme.colors.textWhite
                            )
                        )
                        Text(
                            text = "$3,200.00",
                            style = OffbalanceTheme.typography.moneySmall.copy(
                                color = OffbalanceTheme.colors.textWhite
                            ),
                            color = OffbalanceTheme.colors.textWhite
                        )
                    }
                }
            }

            // Input Field Example
            var textFieldValue by remember { mutableStateOf("") }
            Column {
                Text(
                    text = "Account Name",
                    style = OffbalanceTheme.typography.overline
                )
                Spacer(modifier = Modifier.height(OffbalanceTheme.spacing.sm))
                TextField(
                    value = textFieldValue,
                    onValueChange = { textFieldValue = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(
                            text = "e.g., Main Checking",
                            style = OffbalanceTheme.typography.body2.copy(
                                color = OffbalanceTheme.colors.textMuted
                            )
                        )
                    }
                )
            }

            // Money Input Field Example
            var moneyValue by remember { mutableStateOf("") }
            Column {
                Text(
                    text = "Amount",
                    style = OffbalanceTheme.typography.overline
                )
                Spacer(modifier = Modifier.height(OffbalanceTheme.spacing.sm))
                MoneyTextField(
                    value = moneyValue,
                    onValueChange = { moneyValue = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(
                            text = "$0.00",
                            style = OffbalanceTheme.typography.h4.copy(
                                color = OffbalanceTheme.colors.textMuted
                            )
                        )
                    }
                )
            }

            // Button Examples
            Column(
                verticalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.md)
            ) {
                Button(
                    text = "Update Balance",
                    onClick = { },
                    style = OffbalanceButtonStyle.PRIMARY,
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.md)
                ) {
                    Button(
                        text = "Cancel",
                        onClick = { },
                        style = OffbalanceButtonStyle.SECONDARY,
                        modifier = Modifier.weight(1f)
                    )

                    Button(
                        text = "Delete",
                        onClick = { },
                        style = OffbalanceButtonStyle.DANGER,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // Typography Section
            Card(
                style = OffbalanceCardStyle.SECONDARY,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.md)
                ) {
                    Text(
                        text = "📝 Typography",
                        style = OffbalanceTheme.typography.h2,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Header styles
                    Column(
                        verticalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.sm)
                    ) {
                        Text(
                            text = "Financial Overview",
                            style = OffbalanceTheme.typography.h1
                        )
                        Text(
                            text = "H1 - Header Large (35.2px, Bold)",
                            style = OffbalanceTheme.typography.caption
                        )
                    }

                    Column(
                        verticalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.sm)
                    ) {
                        Text(
                            text = "Account Balance",
                            style = OffbalanceTheme.typography.h2
                        )
                        Text(
                            text = "H2 - Header Medium (25.6px, SemiBold)",
                            style = OffbalanceTheme.typography.caption
                        )
                    }

                    Column(
                        verticalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.sm)
                    ) {
                        Text(
                            text = "$12,847.50",
                            style = OffbalanceTheme.typography.h3
                        )
                        Text(
                            text = "H3 - Display Large (28px, Bold) - Main Balance",
                            style = OffbalanceTheme.typography.caption
                        )
                    }

                    Column(
                        verticalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.sm)
                    ) {
                        Text(
                            text = "$13,200",
                            style = OffbalanceTheme.typography.h4
                        )
                        Text(
                            text = "H4 - Display Medium (18px, SemiBold) - Projections",
                            style = OffbalanceTheme.typography.caption
                        )
                    }

                    Column(
                        verticalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.sm)
                    ) {
                        Text(
                            text = "Recent Balance Updates",
                            style = OffbalanceTheme.typography.h5
                        )
                        Text(
                            text = "H5 - Body Large (14px, SemiBold) - Section Titles",
                            style = OffbalanceTheme.typography.caption
                        )
                    }

                    Column(
                        verticalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.sm)
                    ) {
                        Text(
                            text = "Main Checking",
                            style = OffbalanceTheme.typography.h6
                        )
                        Text(
                            text = "H6 - Body Medium (12px, SemiBold) - Item Titles",
                            style = OffbalanceTheme.typography.caption
                        )
                    }

                    Column(
                        verticalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.sm)
                    ) {
                        Text(
                            text = "This is regular body text for paragraphs and content",
                            style = OffbalanceTheme.typography.body2
                        )
                        Text(
                            text = "Body2 - Standard body text (14px, Normal)",
                            style = OffbalanceTheme.typography.caption
                        )
                    }

                    Column(
                        verticalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.sm)
                    ) {
                        Text(
                            text = "This Month",
                            style = OffbalanceTheme.typography.body1
                        )
                        Text(
                            text = "Body1 - Labels & metadata (11px, Normal)",
                            style = OffbalanceTheme.typography.caption
                        )
                    }

                    Column(
                        verticalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.sm)
                    ) {
                        Text(
                            text = "2 hours ago",
                            style = OffbalanceTheme.typography.caption
                        )
                        Text(
                            text = "Caption - Timestamps (10px, Normal)",
                            style = OffbalanceTheme.typography.caption.copy(
                                color = OffbalanceTheme.colors.textMuted
                            )
                        )
                    }

                    // Specialized money styles
                    Text(
                        text = "💰 Money Typography",
                        style = OffbalanceTheme.typography.h6,
                        modifier = Modifier.padding(top = OffbalanceTheme.spacing.lg)
                    )

                    Column(
                        verticalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.sm)
                    ) {
                        Text(
                            text = "$12,847.50",
                            style = OffbalanceTheme.typography.moneyLarge
                        )
                        Text(
                            text = "Money Large - Main balances (28px, Bold, Monospace)",
                            style = OffbalanceTheme.typography.caption
                        )
                    }

                    Column(
                        verticalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.sm)
                    ) {
                        Text(
                            text = "$1,234.56",
                            style = OffbalanceTheme.typography.moneyMedium
                        )
                        Text(
                            text = "Money Medium - Secondary amounts (18px, SemiBold, Monospace)",
                            style = OffbalanceTheme.typography.caption
                        )
                    }

                    Column(
                        verticalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.sm)
                    ) {
                        Text(
                            text = "$123.45",
                            style = OffbalanceTheme.typography.moneySmall
                        )
                        Text(
                            text = "Money Small - List amounts (14px, SemiBold, Monospace)",
                            style = OffbalanceTheme.typography.caption
                        )
                    }

                    // Status text styles
                    Text(
                        text = "🎯 Status Typography",
                        style = OffbalanceTheme.typography.h6,
                        modifier = Modifier.padding(top = OffbalanceTheme.spacing.lg)
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.md)
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "+$250.00",
                                style = OffbalanceTheme.typography.success
                            )
                            Text(
                                text = "Success",
                                style = OffbalanceTheme.typography.caption
                            )
                        }

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "-$89.99",
                                style = OffbalanceTheme.typography.error
                            )
                            Text(
                                text = "Error",
                                style = OffbalanceTheme.typography.caption
                            )
                        }

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Pending",
                                style = OffbalanceTheme.typography.warning
                            )
                            Text(
                                text = "Warning",
                                style = OffbalanceTheme.typography.caption
                            )
                        }
                    }
                }
            }

            // Color Palette Section
            Card(
                style = OffbalanceCardStyle.SECONDARY,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.lg)
                ) {
                    Text(
                        text = "🎨 Color Palette",
                        style = OffbalanceTheme.typography.h2,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Primary Colors
                    Column(
                        verticalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.md)
                    ) {
                        Text(
                            text = "Primary Colors",
                            style = OffbalanceTheme.typography.h6
                        )

                        // Primary gradient swatch
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.md)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(CircleShape)
                                    .background(OffbalanceTheme.colors.primary)
                                    .border(2.dp, Color.White.copy(alpha = 0.2f), CircleShape)
                            )
                            Column {
                                Text(
                                    text = "Primary Gradient",
                                    style = OffbalanceTheme.typography.body2.copy(
                                        color = OffbalanceTheme.colors.textPrimary
                                    )
                                )
                                Text(
                                    text = "#4F46E5 → #7C3AED",
                                    style = OffbalanceTheme.typography.caption
                                )
                            }
                        }

                        // Primary solid color
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.md)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(CircleShape)
                                    .background(OffbalanceTheme.colors.primary)
                                    .border(2.dp, Color.White.copy(alpha = 0.2f), CircleShape)
                            )
                            Column {
                                Text(
                                    text = "Primary",
                                    style = OffbalanceTheme.typography.body2.copy(
                                        color = OffbalanceTheme.colors.textPrimary
                                    )
                                )
                                Text(
                                    text = "#6366F1",
                                    style = OffbalanceTheme.typography.caption
                                )
                            }
                        }
                    }

                    // Background Colors
                    Column(
                        verticalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.md)
                    ) {
                        Text(
                            text = "Background Colors",
                            style = OffbalanceTheme.typography.h6
                        )

                        // Background gradient
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.md)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(CircleShape)
                                    .background(OffbalanceTheme.colors.background)
                                    .border(2.dp, Color.White.copy(alpha = 0.2f), CircleShape)
                            )
                            Column {
                                Text(
                                    text = "Background Gradient",
                                    style = OffbalanceTheme.typography.body2.copy(
                                        color = OffbalanceTheme.colors.textPrimary
                                    )
                                )
                                Text(
                                    text = "#1A1A2E → #0F3460",
                                    style = OffbalanceTheme.typography.caption
                                )
                            }
                        }

                        // Surface colors row
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.sm)
                        ) {
                            // Surface Dark
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.weight(1f)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clip(CircleShape)
                                        .background(OffbalanceTheme.colors.surfaceDark)
                                        .border(1.dp, Color.White.copy(alpha = 0.2f), CircleShape)
                                )
                                Text(
                                    text = "Surface Dark",
                                    style = OffbalanceTheme.typography.caption,
                                    textAlign = TextAlign.Center
                                )
                            }

                            // Surface Medium
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.weight(1f)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clip(CircleShape)
                                        .background(OffbalanceTheme.colors.surfaceMedium)
                                        .border(1.dp, Color.White.copy(alpha = 0.2f), CircleShape)
                                )
                                Text(
                                    text = "Surface Medium",
                                    style = OffbalanceTheme.typography.caption,
                                    textAlign = TextAlign.Center
                                )
                            }

                            // Surface Light
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.weight(1f)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clip(CircleShape)
                                        .background(OffbalanceTheme.colors.surfaceLight)
                                        .border(1.dp, Color.White.copy(alpha = 0.2f), CircleShape)
                                )
                                Text(
                                    text = "Surface Light",
                                    style = OffbalanceTheme.typography.caption,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }

                    // Text Colors
                    Column(
                        verticalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.md)
                    ) {
                        Text(
                            text = "Text Colors",
                            style = OffbalanceTheme.typography.h6
                        )

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.sm)
                        ) {
                            // Text Primary
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.weight(1f)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clip(CircleShape)
                                        .background(OffbalanceTheme.colors.textPrimary)
                                        .border(1.dp, Color.Black.copy(alpha = 0.2f), CircleShape)
                                )
                                Text(
                                    text = "Primary",
                                    style = OffbalanceTheme.typography.caption,
                                    textAlign = TextAlign.Center,
                                    color = OffbalanceTheme.colors.textPrimary
                                )
                            }

                            // Text Secondary
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.weight(1f)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clip(CircleShape)
                                        .background(OffbalanceTheme.colors.textSecondary)
                                        .border(1.dp, Color.Black.copy(alpha = 0.2f), CircleShape)
                                )
                                Text(
                                    text = "Secondary",
                                    style = OffbalanceTheme.typography.caption,
                                    textAlign = TextAlign.Center,
                                    color = OffbalanceTheme.colors.textSecondary
                                )
                            }

                            // Text Muted
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.weight(1f)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clip(CircleShape)
                                        .background(OffbalanceTheme.colors.textMuted)
                                        .border(1.dp, Color.Black.copy(alpha = 0.2f), CircleShape)
                                )
                                Text(
                                    text = "Muted",
                                    style = OffbalanceTheme.typography.caption,
                                    textAlign = TextAlign.Center,
                                    color = OffbalanceTheme.colors.textMuted
                                )
                            }

                            // Text White
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.weight(1f)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clip(CircleShape)
                                        .background(OffbalanceTheme.colors.textWhite)
                                        .border(1.dp, Color.Black.copy(alpha = 0.2f), CircleShape)
                                )
                                Text(
                                    text = "White",
                                    style = OffbalanceTheme.typography.caption,
                                    textAlign = TextAlign.Center,
                                    color = OffbalanceTheme.colors.textWhite
                                )
                            }
                        }
                    }

                    // Status Colors
                    Column(
                        verticalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.md)
                    ) {
                        Text(
                            text = "Status Colors",
                            style = OffbalanceTheme.typography.h6
                        )

                        // Success colors
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.md)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(CircleShape)
                                    .background(OffbalanceTheme.colors.success)
                                    .border(2.dp, Color.White.copy(alpha = 0.2f), CircleShape)
                            )
                            Column {
                                Text(
                                    text = "Success Gradient",
                                    style = OffbalanceTheme.typography.body2.copy(
                                        color = OffbalanceTheme.colors.success
                                    )
                                )
                                Text(
                                    text = "#059669 → #10B981",
                                    style = OffbalanceTheme.typography.caption
                                )
                            }
                        }

                        // Status colors row
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.sm)
                        ) {
                            // Success
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.weight(1f)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clip(CircleShape)
                                        .background(OffbalanceTheme.colors.success)
                                        .border(1.dp, Color.White.copy(alpha = 0.2f), CircleShape)
                                )
                                Text(
                                    text = "Success",
                                    style = OffbalanceTheme.typography.caption,
                                    textAlign = TextAlign.Center,
                                    color = OffbalanceTheme.colors.success
                                )
                            }

                            // Error
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.weight(1f)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clip(CircleShape)
                                        .background(OffbalanceTheme.colors.error)
                                        .border(1.dp, Color.White.copy(alpha = 0.2f), CircleShape)
                                )
                                Text(
                                    text = "Error",
                                    style = OffbalanceTheme.typography.caption,
                                    textAlign = TextAlign.Center,
                                    color = OffbalanceTheme.colors.error
                                )
                            }

                            // Warning
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.weight(1f)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clip(CircleShape)
                                        .background(OffbalanceTheme.colors.warning)
                                        .border(1.dp, Color.White.copy(alpha = 0.2f), CircleShape)
                                )
                                Text(
                                    text = "Warning",
                                    style = OffbalanceTheme.typography.caption,
                                    textAlign = TextAlign.Center,
                                    color = OffbalanceTheme.colors.warning
                                )
                            }
                        }
                    }

                    // Special Effects
                    Column(
                        verticalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.md)
                    ) {
                        Text(
                            text = "Special Effects",
                            style = OffbalanceTheme.typography.h6
                        )
                    }
                }
            }

            // Glass Card Example
            Card(
                style = OffbalanceCardStyle.GLASS,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "✨ Glassmorphism Card",
                        style = OffbalanceTheme.typography.h6
                    )
                    Text(
                        text = "Modern glass effect design",
                        style = OffbalanceTheme.typography.body1.copy(
                            color = OffbalanceTheme.colors.textSecondary
                        )
                    )
                }
            }
        }
    }
}
