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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
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
import br.alexandregpereira.offbalance.ui.components.OffbalanceTextField
import br.alexandregpereira.offbalance.ui.foundation.OffbalanceColors
import br.alexandregpereira.offbalance.ui.foundation.OffbalanceTextStyles
import br.alexandregpereira.offbalance.ui.foundation.offbalance

@Composable
fun DesignSystemScreen() {
    BackgroundGradient {
        Scaffold(
            backgroundColor = Color.Transparent,
            contentColor = OffbalanceColors.TextPrimary
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(MaterialTheme.offbalance.spacing.screen)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.offbalance.spacing.lg)
            ) {
                // Header
                Text(
                    text = "💰 Offbalance",
                    style = MaterialTheme.typography.h1,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                
                Text(
                    text = "Financial Design System Demo",
                    style = MaterialTheme.typography.body1.copy(
                        color = OffbalanceColors.TextSecondary
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(MaterialTheme.offbalance.spacing.lg))
                
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
                            style = OffbalanceTextStyles.MoneyLarge
                        )
                        Text(
                            text = "Total Balance",
                            style = MaterialTheme.typography.body1.copy(
                                color = OffbalanceColors.TextWhite.copy(alpha = 0.9f)
                            )
                        )
                    }
                }
                
                // Account Cards Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.offbalance.spacing.md)
                ) {
                    OffbalanceCard(
                        style = OffbalanceCardStyle.SECONDARY,
                        modifier = Modifier.weight(1f)
                    ) {
                        Column {
                            Text(
                                text = "🏦",
                                style = MaterialTheme.typography.h4
                            )
                            Text(
                                text = "Main Checking",
                                style = MaterialTheme.typography.h6
                            )
                            Text(
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
                            Text(
                                text = "💰",
                                style = MaterialTheme.typography.h4.copy(
                                    color = OffbalanceColors.TextWhite
                                )
                            )
                            Text(
                                text = "Savings",
                                style = MaterialTheme.typography.h6.copy(
                                    color = OffbalanceColors.TextWhite
                                )
                            )
                            Text(
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
                    Text(
                        text = "Account Name",
                        style = MaterialTheme.typography.overline
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.offbalance.spacing.sm))
                    OffbalanceTextField(
                        value = textFieldValue,
                        onValueChange = { textFieldValue = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text(
                                text = "e.g., Main Checking",
                                style = MaterialTheme.typography.body2.copy(
                                    color = OffbalanceColors.TextMuted
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
                        style = MaterialTheme.typography.overline
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.offbalance.spacing.sm))
                    MoneyTextField(
                        value = moneyValue,
                        onValueChange = { moneyValue = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text(
                                text = "$0.00",
                                style = MaterialTheme.typography.h4.copy(
                                    color = OffbalanceColors.TextMuted
                                )
                            )
                        }
                    )
                }
                
                // Button Examples
                Column(
                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.offbalance.spacing.md)
                ) {
                    OffbalanceButton(
                        onClick = { },
                        style = OffbalanceButtonStyle.PRIMARY,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Update Balance",
                            style = MaterialTheme.typography.button
                        )
                    }
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.offbalance.spacing.md)
                    ) {
                        OffbalanceButton(
                            onClick = { },
                            style = OffbalanceButtonStyle.SECONDARY,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "Cancel",
                                style = MaterialTheme.typography.button.copy(
                                    color = OffbalanceColors.Primary
                                )
                            )
                        }
                        
                        OffbalanceButton(
                            onClick = { },
                            style = OffbalanceButtonStyle.DANGER,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "Delete",
                                style = MaterialTheme.typography.button
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
                        verticalArrangement = Arrangement.spacedBy(MaterialTheme.offbalance.spacing.md)
                    ) {
                        Text(
                            text = "📝 Typography",
                            style = MaterialTheme.typography.h2,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                        
                        // Header styles
                        Column(
                            verticalArrangement = Arrangement.spacedBy(MaterialTheme.offbalance.spacing.sm)
                        ) {
                            Text(
                                text = "Financial Overview",
                                style = MaterialTheme.typography.h1
                            )
                            Text(
                                text = "H1 - Header Large (35.2px, Bold)",
                                style = MaterialTheme.typography.caption
                            )
                        }
                        
                        Column(
                            verticalArrangement = Arrangement.spacedBy(MaterialTheme.offbalance.spacing.sm)
                        ) {
                            Text(
                                text = "Account Balance",
                                style = MaterialTheme.typography.h2
                            )
                            Text(
                                text = "H2 - Header Medium (25.6px, SemiBold)",
                                style = MaterialTheme.typography.caption
                            )
                        }
                        
                        Column(
                            verticalArrangement = Arrangement.spacedBy(MaterialTheme.offbalance.spacing.sm)
                        ) {
                            Text(
                                text = "$12,847.50",
                                style = MaterialTheme.typography.h3
                            )
                            Text(
                                text = "H3 - Display Large (28px, Bold) - Main Balance",
                                style = MaterialTheme.typography.caption
                            )
                        }
                        
                        Column(
                            verticalArrangement = Arrangement.spacedBy(MaterialTheme.offbalance.spacing.sm)
                        ) {
                            Text(
                                text = "$13,200",
                                style = MaterialTheme.typography.h4
                            )
                            Text(
                                text = "H4 - Display Medium (18px, SemiBold) - Projections",
                                style = MaterialTheme.typography.caption
                            )
                        }
                        
                        Column(
                            verticalArrangement = Arrangement.spacedBy(MaterialTheme.offbalance.spacing.sm)
                        ) {
                            Text(
                                text = "Recent Balance Updates",
                                style = MaterialTheme.typography.h5
                            )
                            Text(
                                text = "H5 - Body Large (14px, SemiBold) - Section Titles",
                                style = MaterialTheme.typography.caption
                            )
                        }
                        
                        Column(
                            verticalArrangement = Arrangement.spacedBy(MaterialTheme.offbalance.spacing.sm)
                        ) {
                            Text(
                                text = "Main Checking",
                                style = MaterialTheme.typography.h6
                            )
                            Text(
                                text = "H6 - Body Medium (12px, SemiBold) - Item Titles",
                                style = MaterialTheme.typography.caption
                            )
                        }
                        
                        Column(
                            verticalArrangement = Arrangement.spacedBy(MaterialTheme.offbalance.spacing.sm)
                        ) {
                            Text(
                                text = "This is regular body text for paragraphs and content",
                                style = MaterialTheme.typography.body2
                            )
                            Text(
                                text = "Body2 - Standard body text (14px, Normal)",
                                style = MaterialTheme.typography.caption
                            )
                        }
                        
                        Column(
                            verticalArrangement = Arrangement.spacedBy(MaterialTheme.offbalance.spacing.sm)
                        ) {
                            Text(
                                text = "This Month",
                                style = MaterialTheme.typography.body1
                            )
                            Text(
                                text = "Body1 - Labels & metadata (11px, Normal)",
                                style = MaterialTheme.typography.caption
                            )
                        }
                        
                        Column(
                            verticalArrangement = Arrangement.spacedBy(MaterialTheme.offbalance.spacing.sm)
                        ) {
                            Text(
                                text = "2 hours ago",
                                style = MaterialTheme.typography.caption
                            )
                            Text(
                                text = "Caption - Timestamps (10px, Normal)",
                                style = MaterialTheme.typography.caption.copy(
                                    color = OffbalanceColors.TextMuted
                                )
                            )
                        }
                        
                        // Specialized money styles
                        Text(
                            text = "💰 Money Typography",
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier.padding(top = MaterialTheme.offbalance.spacing.lg)
                        )
                        
                        Column(
                            verticalArrangement = Arrangement.spacedBy(MaterialTheme.offbalance.spacing.sm)
                        ) {
                            Text(
                                text = "$12,847.50",
                                style = OffbalanceTextStyles.MoneyLarge
                            )
                            Text(
                                text = "Money Large - Main balances (28px, Bold, Monospace)",
                                style = MaterialTheme.typography.caption
                            )
                        }
                        
                        Column(
                            verticalArrangement = Arrangement.spacedBy(MaterialTheme.offbalance.spacing.sm)
                        ) {
                            Text(
                                text = "$1,234.56",
                                style = OffbalanceTextStyles.MoneyMedium
                            )
                            Text(
                                text = "Money Medium - Secondary amounts (18px, SemiBold, Monospace)",
                                style = MaterialTheme.typography.caption
                            )
                        }
                        
                        Column(
                            verticalArrangement = Arrangement.spacedBy(MaterialTheme.offbalance.spacing.sm)
                        ) {
                            Text(
                                text = "$123.45",
                                style = OffbalanceTextStyles.MoneySmall
                            )
                            Text(
                                text = "Money Small - List amounts (14px, SemiBold, Monospace)",
                                style = MaterialTheme.typography.caption
                            )
                        }
                        
                        // Status text styles
                        Text(
                            text = "🎯 Status Typography",
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier.padding(top = MaterialTheme.offbalance.spacing.lg)
                        )
                        
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.offbalance.spacing.md)
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "+$250.00",
                                    style = OffbalanceTextStyles.Success
                                )
                                Text(
                                    text = "Success",
                                    style = MaterialTheme.typography.caption
                                )
                            }
                            
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "-$89.99",
                                    style = OffbalanceTextStyles.Error
                                )
                                Text(
                                    text = "Error",
                                    style = MaterialTheme.typography.caption
                                )
                            }
                            
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Pending",
                                    style = OffbalanceTextStyles.Warning
                                )
                                Text(
                                    text = "Warning",
                                    style = MaterialTheme.typography.caption
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
                        Text(
                            text = "✨ Glassmorphism Card",
                            style = MaterialTheme.typography.h6
                        )
                        Text(
                            text = "Modern glass effect design",
                            style = MaterialTheme.typography.body1.copy(
                                color = OffbalanceColors.TextSecondary
                            )
                        )
                    }
                }
            }
        }
    }
}
