package br.alexandregpereira.offbalance.feature.history.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import br.alexandregpereira.offbalance.feature.history.EmptyHistoryIntent
import br.alexandregpereira.offbalance.feature.history.HistoryIntent
import br.alexandregpereira.offbalance.feature.history.HistoryState
import br.alexandregpereira.offbalance.feature.history.SnapshotState
import br.alexandregpereira.offbalance.ui.components.Button
import br.alexandregpereira.offbalance.ui.components.Card
import br.alexandregpereira.offbalance.ui.components.OffbalanceButtonStyle
import br.alexandregpereira.offbalance.ui.components.OffbalanceCardStyle
import br.alexandregpereira.offbalance.ui.components.Text
import br.alexandregpereira.offbalance.ui.components.VerticalSpace
import br.alexandregpereira.offbalance.ui.foundation.OffbalanceTheme

@Composable
fun HistoryScreen(
    state: HistoryState,
    intent: HistoryIntent = EmptyHistoryIntent(),
    contentPadding: PaddingValues = PaddingValues(),
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = contentPadding,
    ) {
        item {
            Column(modifier = Modifier.padding(OffbalanceTheme.spacing.screen)) {
                Text(text = "Histórico", style = OffbalanceTheme.typography.h4)
                Text(
                    text = "Evolução mensal do patrimônio",
                    style = OffbalanceTheme.typography.caption,
                    color = OffbalanceTheme.colors.textMuted,
                )
            }
        }

        state.errorMessage?.let { message ->
            item {
                Column(modifier = Modifier.padding(horizontal = OffbalanceTheme.spacing.screen)) {
                    Text(text = message, color = OffbalanceTheme.colors.error)
                    VerticalSpace(OffbalanceTheme.spacing.md)
                    Button(
                        text = "Tentar de novo",
                        onClick = intent::onRetryClick,
                        style = OffbalanceButtonStyle.SECONDARY,
                    )
                }
            }
        }

        if (state.isEmpty) {
            item {
                Text(
                    text = "Nenhum saldo registrado ainda. Sincronize na aba de configurações.",
                    modifier = Modifier.padding(horizontal = OffbalanceTheme.spacing.screen),
                    color = OffbalanceTheme.colors.textMuted,
                )
            }
        }

        items(state.snapshots, key = { it.label }) { snapshot ->
            SnapshotRow(snapshot)
        }

        item { VerticalSpace(OffbalanceTheme.spacing.xxxl) }
    }
}

@Composable
private fun SnapshotRow(snapshot: SnapshotState) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = OffbalanceTheme.spacing.screen,
                vertical = OffbalanceTheme.spacing.xs,
            ),
        style = OffbalanceCardStyle.SECONDARY,
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = snapshot.label, style = OffbalanceTheme.typography.body1)
                Text(text = snapshot.total, style = OffbalanceTheme.typography.moneySmall)
            }
            VerticalSpace(OffbalanceTheme.spacing.sm)
            BalanceBar(relativeSize = snapshot.relativeSize)
            snapshot.change?.let { change ->
                VerticalSpace(OffbalanceTheme.spacing.xs)
                Text(
                    text = change,
                    style = OffbalanceTheme.typography.caption,
                    color = if (snapshot.isPositiveChange) {
                        OffbalanceTheme.colors.success
                    } else {
                        OffbalanceTheme.colors.error
                    },
                )
            }
        }
    }
}

@Composable
private fun BalanceBar(relativeSize: Float) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(OffbalanceTheme.spacing.xs)
            .background(
                color = OffbalanceTheme.colors.surfaceLight,
                shape = OffbalanceTheme.shapes.button,
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(fraction = relativeSize)
                .height(OffbalanceTheme.spacing.xs)
                .background(
                    color = OffbalanceTheme.colors.primary,
                    shape = OffbalanceTheme.shapes.button,
                )
        )
    }
}
