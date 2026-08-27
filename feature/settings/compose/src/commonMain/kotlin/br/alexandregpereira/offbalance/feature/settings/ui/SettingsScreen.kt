package br.alexandregpereira.offbalance.feature.settings.ui

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
import br.alexandregpereira.offbalance.feature.settings.ConnectionState
import br.alexandregpereira.offbalance.feature.settings.EmptySettingsIntent
import br.alexandregpereira.offbalance.feature.settings.ProviderOptionState
import br.alexandregpereira.offbalance.feature.settings.SettingsIntent
import br.alexandregpereira.offbalance.feature.settings.SettingsState
import br.alexandregpereira.offbalance.ui.components.Button
import br.alexandregpereira.offbalance.ui.components.Card
import br.alexandregpereira.offbalance.ui.components.OffbalanceButtonStyle
import br.alexandregpereira.offbalance.ui.components.OffbalanceCardStyle
import br.alexandregpereira.offbalance.ui.components.Text
import br.alexandregpereira.offbalance.ui.components.VerticalSpace
import br.alexandregpereira.offbalance.ui.foundation.OffbalanceTheme

@Composable
fun SettingsScreen(
    state: SettingsState,
    intent: SettingsIntent = EmptySettingsIntent(),
    contentPadding: PaddingValues = PaddingValues(),
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = contentPadding,
    ) {
        item {
            Column(modifier = Modifier.padding(OffbalanceTheme.spacing.screen)) {
                Text(text = "Configurações", style = OffbalanceTheme.typography.h4)
                VerticalSpace(OffbalanceTheme.spacing.md)
                Button(
                    text = if (state.isSyncing) "Sincronizando..." else "Sincronizar agora",
                    onClick = intent::onSyncClick,
                    enabled = !state.isSyncing,
                )
                state.message?.let { message ->
                    VerticalSpace(OffbalanceTheme.spacing.sm)
                    Text(
                        text = message,
                        style = OffbalanceTheme.typography.caption,
                        color = OffbalanceTheme.colors.textSecondary,
                    )
                }
            }
        }

        item { SectionTitle("Provedor de open finance") }

        items(state.providers, key = { it.type.name }) { provider ->
            ProviderRow(
                provider = provider,
                onClick = { intent.onProviderClick(provider.type) },
            )
        }

        item { SectionTitle("Conexões") }

        if (state.connections.isEmpty()) {
            item {
                Text(
                    text = "Nenhuma instituição conectada. Sincronize para importar as contas.",
                    modifier = Modifier.padding(horizontal = OffbalanceTheme.spacing.screen),
                    color = OffbalanceTheme.colors.textMuted,
                )
            }
        }

        items(state.connections, key = { it.id }) { connection ->
            ConnectionRow(connection)
        }

        item { VerticalSpace(OffbalanceTheme.spacing.xxxl) }
    }
}

@Composable
private fun SectionTitle(title: String) {
    Text(
        text = title,
        style = OffbalanceTheme.typography.overline,
        color = OffbalanceTheme.colors.textMuted,
        modifier = Modifier.padding(
            start = OffbalanceTheme.spacing.screen,
            end = OffbalanceTheme.spacing.screen,
            top = OffbalanceTheme.spacing.lg,
            bottom = OffbalanceTheme.spacing.sm,
        ),
    )
}

@Composable
private fun ProviderRow(provider: ProviderOptionState, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = OffbalanceTheme.spacing.screen,
                vertical = OffbalanceTheme.spacing.xs,
            ),
        style = if (provider.isSelected) {
            OffbalanceCardStyle.PRIMARY
        } else {
            OffbalanceCardStyle.SECONDARY
        },
        onClick = onClick.takeIf { provider.isAvailable },
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.fillMaxWidth(fraction = 0.8f)) {
                Text(
                    text = provider.name,
                    style = OffbalanceTheme.typography.body1,
                    color = if (provider.isAvailable) {
                        OffbalanceTheme.colors.textPrimary
                    } else {
                        OffbalanceTheme.colors.textMuted
                    },
                )
                Text(
                    text = provider.description,
                    style = OffbalanceTheme.typography.caption,
                    color = OffbalanceTheme.colors.textSecondary,
                )
            }
            if (provider.isSelected) {
                Text(
                    text = "Ativo",
                    style = OffbalanceTheme.typography.caption,
                    color = OffbalanceTheme.colors.textWhite,
                )
            }
        }
    }
}

@Composable
private fun ConnectionRow(connection: ConnectionState) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = OffbalanceTheme.spacing.screen,
                vertical = OffbalanceTheme.spacing.xs,
            ),
        style = OffbalanceCardStyle.SECONDARY,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = connection.institutionName, style = OffbalanceTheme.typography.body1)
            Text(
                text = connection.statusLabel,
                style = OffbalanceTheme.typography.caption,
                color = OffbalanceTheme.colors.textSecondary,
            )
        }
    }
}
