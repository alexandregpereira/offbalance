package br.alexandregpereira.offbalance.feature.history

import br.alexandregpereira.offbalance.balance.model.NetWorthSnapshot
import br.alexandregpereira.offbalance.money.format

data class HistoryState(
    val isLoading: Boolean = true,
    val snapshots: List<SnapshotState> = emptyList(),
    val errorMessage: String? = null,
) {
    val isEmpty: Boolean = !isLoading && errorMessage == null && snapshots.isEmpty()
}

data class SnapshotState(
    val label: String,
    val total: String,
    val change: String?,
    val isPositiveChange: Boolean,
    /** Bar length relative to the largest snapshot in the series, from 0f to 1f. */
    val relativeSize: Float,
)

internal fun HistoryState.loading(): HistoryState = copy(isLoading = true, errorMessage = null)

internal fun HistoryState.failure(message: String): HistoryState =
    copy(isLoading = false, errorMessage = message)

internal fun HistoryState.content(snapshots: List<NetWorthSnapshot>): HistoryState {
    val maxCents = snapshots.maxOfOrNull { it.total.cents }?.takeIf { it > 0 } ?: 1L

    return copy(
        isLoading = false,
        errorMessage = null,
        snapshots = snapshots.mapIndexed { index, snapshot ->
            val previous = snapshots.getOrNull(index - 1)
            val changeCents = previous?.let { snapshot.total.cents - it.total.cents }

            SnapshotState(
                label = snapshot.date.monthLabel(),
                total = snapshot.total.format(),
                change = changeCents?.let {
                    val prefix = if (it >= 0) "+" else ""
                    prefix + snapshot.total.copy(cents = it).format()
                },
                isPositiveChange = (changeCents ?: 0L) >= 0L,
                relativeSize = (snapshot.total.cents.toFloat() / maxCents).coerceIn(0f, 1f),
            )
        }.reversed(),
    )
}

private fun kotlinx.datetime.LocalDate.monthLabel(): String {
    val month = when (monthNumber) {
        1 -> "Jan"
        2 -> "Fev"
        3 -> "Mar"
        4 -> "Abr"
        5 -> "Mai"
        6 -> "Jun"
        7 -> "Jul"
        8 -> "Ago"
        9 -> "Set"
        10 -> "Out"
        11 -> "Nov"
        else -> "Dez"
    }
    return "$month $year"
}
