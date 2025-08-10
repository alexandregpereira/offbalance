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
import androidx.compose.ui.text.style.TextAlign
import br.alexandregpereira.offbalance.ui.foundation.OffbalanceColors
import br.alexandregpereira.offbalance.ui.foundation.OffbalanceTextStyles
import br.alexandregpereira.offbalance.ui.foundation.components.BackgroundGradient
import br.alexandregpereira.offbalance.ui.foundation.components.FinancialCard
import br.alexandregpereira.offbalance.ui.foundation.components.MoneyTextField
import br.alexandregpereira.offbalance.ui.foundation.components.OffbalanceButton
import br.alexandregpereira.offbalance.ui.foundation.components.OffbalanceButtonStyle
import br.alexandregpereira.offbalance.ui.foundation.components.OffbalanceCard
import br.alexandregpereira.offbalance.ui.foundation.components.OffbalanceCardStyle
import br.alexandregpereira.offbalance.ui.foundation.components.OffbalanceTextField
import br.alexandregpereira.offbalance.ui.foundation.offbalance

@Composable
fun DesignSystemScreen() {
    BackgroundGradient {
        Scaffold(
            backgroundColor = androidx.compose.ui.graphics.Color.Transparent,
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
