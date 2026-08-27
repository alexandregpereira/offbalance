package br.alexandregpereira.offbalance.ui.foundation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ShowChart
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * The icons the app uses, re-exported as plain [ImageVector]s so no screen has to depend on
 * Material directly.
 */
object OffbalanceIcons {
    val Home: ImageVector = Icons.Filled.Home
    val History: ImageVector = Icons.AutoMirrored.Filled.ShowChart
    val Settings: ImageVector = Icons.Filled.Settings
    val Refresh: ImageVector = Icons.Filled.Refresh

    val Checking: ImageVector = Icons.Filled.AccountBalance
    val Savings: ImageVector = Icons.Filled.Savings
    val CreditCard: ImageVector = Icons.Filled.CreditCard
    val Investment: ImageVector = Icons.AutoMirrored.Filled.TrendingUp
    val Cash: ImageVector = Icons.Filled.AccountBalanceWallet
}
