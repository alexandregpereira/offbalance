package br.alexandregpereira.offbalance.feature.dashboard.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import br.alexandregpereira.offbalance.feature.dashboard.AccountState
import br.alexandregpereira.offbalance.feature.dashboard.DashboardIntent
import br.alexandregpereira.offbalance.feature.dashboard.DashboardState
import br.alexandregpereira.offbalance.feature.dashboard.EmptyDashboardIntent
import br.alexandregpereira.offbalance.feature.dashboard.InstitutionGroupState
import br.alexandregpereira.offbalance.ui.components.Button
import br.alexandregpereira.offbalance.ui.components.Card
import br.alexandregpereira.offbalance.ui.components.OffbalanceButtonStyle
import br.alexandregpereira.offbalance.ui.components.OffbalanceCardStyle
import br.alexandregpereira.offbalance.ui.components.Text
import br.alexandregpereira.offbalance.ui.components.VerticalSpace
import br.alexandregpereira.offbalance.ui.foundation.OffbalanceTheme

@Composable
fun DashboardScreen(
    state: DashboardState,
    intent: DashboardIntent = EmptyDashboardIntent(),
    contentPadding: PaddingValues = PaddingValues(),
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = contentPadding,
    ) {
        item {
            Column(modifier = Modifier.padding(OffbalanceTheme.spacing.screen)) {
                Text(
                    text = "Patrimônio",
                    style = OffbalanceTheme.typography.h4,
                )
                VerticalSpace(OffbalanceTheme.spacing.md)
                NetWorthCard(state = state, onSyncClick = intent::onSyncClick)
            }
        }

        state.errorMessage?.let { message ->
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = OffbalanceTheme.spacing.screen),
                    style = OffbalanceCardStyle.SECONDARY,
                ) {
                    Text(text = message, color = OffbalanceTheme.colors.error)
                }
            }
        }

        items(state.institutions, key = { it.id }) { institution ->
            InstitutionSection(institution)
        }

        item { VerticalSpace(OffbalanceTheme.spacing.xxxl) }
    }
}

@Composable
private fun NetWorthCard(state: DashboardState, onSyncClick: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth(), style = OffbalanceCardStyle.PRIMARY) {
        Column {
            Text(
                text = "Saldo total",
                color = OffbalanceTheme.colors.textWhite,
                style = OffbalanceTheme.typography.overline,
            )
            VerticalSpace(OffbalanceTheme.spacing.sm)
            Text(
                text = if (state.isLoading) "—" else state.netWorth,
                color = OffbalanceTheme.colors.textWhite,
                style = OffbalanceTheme.typography.moneyLarge,
            )
            VerticalSpace(OffbalanceTheme.spacing.lg)
            Button(
                text = if (state.isSyncing) "Sincronizando..." else "Sincronizar",
                onClick = onSyncClick,
                enabled = !state.isSyncing,
                style = OffbalanceButtonStyle.SECONDARY,
            )
        }
    }
}

@Composable
private fun InstitutionSection(institution: InstitutionGroupState) {
    Column(
        modifier = Modifier.padding(
            horizontal = OffbalanceTheme.spacing.screen,
            vertical = OffbalanceTheme.spacing.sm,
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = institution.name, style = OffbalanceTheme.typography.h6)
            Text(
                text = institution.total,
                style = OffbalanceTheme.typography.moneySmall,
                color = OffbalanceTheme.colors.textSecondary,
            )
        }
        VerticalSpace(OffbalanceTheme.spacing.sm)
        institution.accounts.forEach { account ->
            AccountRow(account)
            VerticalSpace(OffbalanceTheme.spacing.sm)
        }
    }
}

@Composable
private fun AccountRow(account: AccountState) {
    Card(modifier = Modifier.fillMaxWidth(), style = OffbalanceCardStyle.SECONDARY) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column {
                Text(text = account.name, style = OffbalanceTheme.typography.body1)
                Text(
                    text = account.typeLabel,
                    style = OffbalanceTheme.typography.caption,
                    color = OffbalanceTheme.colors.textMuted,
                )
            }
            Text(
                text = account.balance,
                style = OffbalanceTheme.typography.moneySmall,
                color = if (account.isNegative) {
                    OffbalanceTheme.colors.error
                } else {
                    OffbalanceTheme.colors.textPrimary
                },
            )
        }
    }
}
