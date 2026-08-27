package br.alexandregpereira.offbalance.feature.history

interface HistoryIntent {

    fun onRetryClick()
}

class EmptyHistoryIntent : HistoryIntent {

    override fun onRetryClick() = Unit
}
