package br.alexandregpereira.offbalance.ui.app

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import br.alexandregpereira.offbalance.ui.components.BackgroundGradient
import br.alexandregpereira.offbalance.ui.components.FinancialCard
import br.alexandregpereira.offbalance.ui.components.MoneyTextField
import br.alexandregpereira.offbalance.ui.components.OffbalanceButton
import br.alexandregpereira.offbalance.ui.components.OffbalanceButtonStyle
import br.alexandregpereira.offbalance.ui.components.OffbalanceCard
import br.alexandregpereira.offbalance.ui.components.OffbalanceCardStyle
import br.alexandregpereira.offbalance.ui.components.OffbalanceScaffold
import br.alexandregpereira.offbalance.ui.components.OffbalanceText
import br.alexandregpereira.offbalance.ui.components.OffbalanceTextField
import br.alexandregpereira.offbalance.ui.foundation.OffbalanceColors
import br.alexandregpereira.offbalance.ui.foundation.OffbalanceTextStyles
import br.alexandregpereira.offbalance.ui.foundation.OffbalanceTheme

@Composable
fun DesignSystemScreen() {
    BackgroundGradient {
        OffbalanceScaffold(
            backgroundColor = Color.Transparent,
            contentColor = OffbalanceColors.TextPrimary
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(OffbalanceTheme.spacing.screen)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.lg)
            ) {
                // Header
                OffbalanceText(
                    text = "💰 Offbalance",
                    style = OffbalanceTheme.typography.h1,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                
                OffbalanceText(
                    text = "Financial Design System Demo",
                    style = OffbalanceTheme.typography.body1.copy(
                        color = OffbalanceColors.TextSecondary
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
                        OffbalanceText(
                            text = "$12,847.50",
                            style = OffbalanceTextStyles.MoneyLarge
                        )
                        OffbalanceText(
                            text = "Total Balance",
                            style = OffbalanceTheme.typography.body1.copy(
                                color = OffbalanceColors.TextWhite.copy(alpha = 0.9f)
                            )
                        )
                    }
                }
                
                // Account Cards Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.md)
                ) {
                    OffbalanceCard(
                        style = OffbalanceCardStyle.SECONDARY,
                        modifier = Modifier.weight(1f)
                    ) {
                        Column {
                            OffbalanceText(
                                text = "🏦",
                                style = OffbalanceTheme.typography.h4
                            )
                            OffbalanceText(
                                text = "Main Checking",
                                style = OffbalanceTheme.typography.h6
                            )
                            OffbalanceText(
                                text = "$8,247.50",
                                style = OffbalanceTextStyles.MoneySmall
                            )
                        }
                    }
                    
                    OffbalanceCard(
                        style = OffbalanceCardStyle.SUCCESS,
                        modifier = Modifier.weight(1f)
                    ) {
                        Column {
                            OffbalanceText(
                                text = "💰",
                                style = OffbalanceTheme.typography.h4.copy(
                                    color = OffbalanceColors.TextWhite
                                )
                            )
                            OffbalanceText(
                                text = "Savings",
                                style = OffbalanceTheme.typography.h6.copy(
                                    color = OffbalanceColors.TextWhite
                                )
                            )
                            OffbalanceText(
                                text = "$3,200.00",
                                style = OffbalanceTextStyles.MoneySmall.copy(
                                    color = OffbalanceColors.TextWhite
                                )
                            )
                        }
                    }
                }
                
                // Input Field Example
                var textFieldValue by remember { mutableStateOf("") }
                Column {
                    OffbalanceText(
                        text = "Account Name",
                        style = OffbalanceTheme.typography.overline
                    )
                    Spacer(modifier = Modifier.height(OffbalanceTheme.spacing.sm))
                    OffbalanceTextField(
                        value = textFieldValue,
                        onValueChange = { textFieldValue = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            OffbalanceText(
                                text = "e.g., Main Checking",
                                style = OffbalanceTheme.typography.body2.copy(
                                    color = OffbalanceColors.TextMuted
                                )
                            )
                        }
                    )
                }
                
                // Money Input Field Example
                var moneyValue by remember { mutableStateOf("") }
                Column {
                    OffbalanceText(
                        text = "Amount",
                        style = OffbalanceTheme.typography.overline
                    )
                    Spacer(modifier = Modifier.height(OffbalanceTheme.spacing.sm))
                    MoneyTextField(
                        value = moneyValue,
                        onValueChange = { moneyValue = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            OffbalanceText(
                                text = "$0.00",
                                style = OffbalanceTheme.typography.h4.copy(
                                    color = OffbalanceColors.TextMuted
                                )
                            )
                        }
                    )
                }
                
                // Button Examples
                Column(
                    verticalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.md)
                ) {
                    OffbalanceButton(
                        onClick = { },
                        style = OffbalanceButtonStyle.PRIMARY,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OffbalanceText(
                            text = "Update Balance",
                            style = OffbalanceTheme.typography.button
                        )
                    }
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.md)
                    ) {
                        OffbalanceButton(
                            onClick = { },
                            style = OffbalanceButtonStyle.SECONDARY,
                            modifier = Modifier.weight(1f)
                        ) {
                            OffbalanceText(
                                text = "Cancel",
                                style = OffbalanceTheme.typography.button.copy(
                                    color = OffbalanceColors.Primary
                                )
                            )
                        }
                        
                        OffbalanceButton(
                            onClick = { },
                            style = OffbalanceButtonStyle.DANGER,
                            modifier = Modifier.weight(1f)
                        ) {
                            OffbalanceText(
                                text = "Delete",
                                style = OffbalanceTheme.typography.button
                            )
                        }
                    }
                }
                
                // Typography Section
                OffbalanceCard(
                    style = OffbalanceCardStyle.SECONDARY,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.md)
                    ) {
                        OffbalanceText(
                            text = "📝 Typography",
                            style = OffbalanceTheme.typography.h2,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                        
                        // Header styles
                        Column(
                            verticalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.sm)
                        ) {
                            OffbalanceText(
                                text = "Financial Overview",
                                style = OffbalanceTheme.typography.h1
                            )
                            OffbalanceText(
                                text = "H1 - Header Large (35.2px, Bold)",
                                style = OffbalanceTheme.typography.caption
                            )
                        }
                        
                        Column(
                            verticalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.sm)
                        ) {
                            OffbalanceText(
                                text = "Account Balance",
                                style = OffbalanceTheme.typography.h2
                            )
                            OffbalanceText(
                                text = "H2 - Header Medium (25.6px, SemiBold)",
                                style = OffbalanceTheme.typography.caption
                            )
                        }
                        
                        Column(
                            verticalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.sm)
                        ) {
                            OffbalanceText(
                                text = "$12,847.50",
                                style = OffbalanceTheme.typography.h3
                            )
                            OffbalanceText(
                                text = "H3 - Display Large (28px, Bold) - Main Balance",
                                style = OffbalanceTheme.typography.caption
                            )
                        }
                        
                        Column(
                            verticalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.sm)
                        ) {
                            OffbalanceText(
                                text = "$13,200",
                                style = OffbalanceTheme.typography.h4
                            )
                            OffbalanceText(
                                text = "H4 - Display Medium (18px, SemiBold) - Projections",
                                style = OffbalanceTheme.typography.caption
                            )
                        }
                        
                        Column(
                            verticalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.sm)
                        ) {
                            OffbalanceText(
                                text = "Recent Balance Updates",
                                style = OffbalanceTheme.typography.h5
                            )
                            OffbalanceText(
                                text = "H5 - Body Large (14px, SemiBold) - Section Titles",
                                style = OffbalanceTheme.typography.caption
                            )
                        }
                        
                        Column(
                            verticalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.sm)
                        ) {
                            OffbalanceText(
                                text = "Main Checking",
                                style = OffbalanceTheme.typography.h6
                            )
                            OffbalanceText(
                                text = "H6 - Body Medium (12px, SemiBold) - Item Titles",
                                style = OffbalanceTheme.typography.caption
                            )
                        }
                        
                        Column(
                            verticalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.sm)
                        ) {
                            OffbalanceText(
                                text = "This is regular body text for paragraphs and content",
                                style = OffbalanceTheme.typography.body2
                            )
                            OffbalanceText(
                                text = "Body2 - Standard body text (14px, Normal)",
                                style = OffbalanceTheme.typography.caption
                            )
                        }
                        
                        Column(
                            verticalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.sm)
                        ) {
                            OffbalanceText(
                                text = "This Month",
                                style = OffbalanceTheme.typography.body1
                            )
                            OffbalanceText(
                                text = "Body1 - Labels & metadata (11px, Normal)",
                                style = OffbalanceTheme.typography.caption
                            )
                        }
                        
                        Column(
                            verticalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.sm)
                        ) {
                            OffbalanceText(
                                text = "2 hours ago",
                                style = OffbalanceTheme.typography.caption
                            )
                            OffbalanceText(
                                text = "Caption - Timestamps (10px, Normal)",
                                style = OffbalanceTheme.typography.caption.copy(
                                    color = OffbalanceColors.TextMuted
                                )
                            )
                        }
                        
                        // Specialized money styles
                        OffbalanceText(
                            text = "💰 Money Typography",
                            style = OffbalanceTheme.typography.h6,
                            modifier = Modifier.padding(top = OffbalanceTheme.spacing.lg)
                        )
                        
                        Column(
                            verticalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.sm)
                        ) {
                            OffbalanceText(
                                text = "$12,847.50",
                                style = OffbalanceTextStyles.MoneyLarge
                            )
                            OffbalanceText(
                                text = "Money Large - Main balances (28px, Bold, Monospace)",
                                style = OffbalanceTheme.typography.caption
                            )
                        }
                        
                        Column(
                            verticalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.sm)
                        ) {
                            OffbalanceText(
                                text = "$1,234.56",
                                style = OffbalanceTextStyles.MoneyMedium
                            )
                            OffbalanceText(
                                text = "Money Medium - Secondary amounts (18px, SemiBold, Monospace)",
                                style = OffbalanceTheme.typography.caption
                            )
                        }
                        
                        Column(
                            verticalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.sm)
                        ) {
                            OffbalanceText(
                                text = "$123.45",
                                style = OffbalanceTextStyles.MoneySmall
                            )
                            OffbalanceText(
                                text = "Money Small - List amounts (14px, SemiBold, Monospace)",
                                style = OffbalanceTheme.typography.caption
                            )
                        }
                        
                        // Status text styles
                        OffbalanceText(
                            text = "🎯 Status Typography",
                            style = OffbalanceTheme.typography.h6,
                            modifier = Modifier.padding(top = OffbalanceTheme.spacing.lg)
                        )
                        
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(OffbalanceTheme.spacing.md)
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                OffbalanceText(
                                    text = "+$250.00",
                                    style = OffbalanceTextStyles.Success
                                )
                                OffbalanceText(
                                    text = "Success",
                                    style = OffbalanceTheme.typography.caption
                                )
                            }
                            
                            Column(modifier = Modifier.weight(1f)) {
                                OffbalanceText(
                                    text = "-$89.99",
                                    style = OffbalanceTextStyles.Error
                                )
                                OffbalanceText(
                                    text = "Error",
                                    style = OffbalanceTheme.typography.caption
                                )
                            }
                            
                            Column(modifier = Modifier.weight(1f)) {
                                OffbalanceText(
                                    text = "Pending",
                                    style = OffbalanceTextStyles.Warning
                                )
                                OffbalanceText(
                                    text = "Warning",
                                    style = OffbalanceTheme.typography.caption
                                )
                            }
                        }
                    }
                }
                
                // Glass Card Example
                OffbalanceCard(
                    style = OffbalanceCardStyle.GLASS,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        OffbalanceText(
                            text = "✨ Glassmorphism Card",
                            style = OffbalanceTheme.typography.h6
                        )
                        OffbalanceText(
                            text = "Modern glass effect design",
                            style = OffbalanceTheme.typography.body1.copy(
                                color = OffbalanceColors.TextSecondary
                            )
                        )
                    }
                }
            }
        }
    }
}
