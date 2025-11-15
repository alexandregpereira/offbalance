package br.alexandregpereira.offbalance

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.alexandregpereira.offbalance.ui.components.Button
import br.alexandregpereira.offbalance.ui.components.Card
import br.alexandregpereira.offbalance.ui.components.Scaffold
import br.alexandregpereira.offbalance.ui.components.Text
import br.alexandregpereira.offbalance.ui.components.VerticalSpace
import br.alexandregpereira.offbalance.ui.foundation.OffbalanceTheme

@Composable
fun OffbalanceApp() {
    Scaffold {
        VerticalSpace(height = 32.dp)
        Column(
            verticalArrangement = Arrangement.spacedBy(space = 16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                onClick = {}
            ) {
                Column {
                    Text(
                        text = "Total",
                        style = OffbalanceTheme.typography.body2,
                    )
                    VerticalSpace(height = 8.dp)
                    Text(
                        text = "R$ 10.000,00",
                        style = OffbalanceTheme.typography.moneyLarge,
                    )
                }
            }
            Button(
                text = "Adicionar",
                onClick = {}
            )
            Card(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = "R$ 10.000,00",
                    style = OffbalanceTheme.typography.moneyLarge,
                )
            }
        }
    }
}