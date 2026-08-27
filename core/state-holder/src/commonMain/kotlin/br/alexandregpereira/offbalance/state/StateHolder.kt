package br.alexandregpereira.offbalance.state

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

interface StateHolder<State : Any> {

    val state: StateFlow<State>
}

abstract class UiModel<State : Any>(
    initialState: State,
) : StateHolder<State> {

    protected val scope: CoroutineScope = createScope()

    private val _state = MutableStateFlow(initialState)
    override val state: StateFlow<State> = _state.asStateFlow()

    protected fun setState(block: State.() -> State) {
        _state.value = _state.value.block()
    }

    open fun onCleared() {
        scope.cancel()
    }

    private fun createScope(): CoroutineScope = CoroutineScope(
        SupervisorJob() + Dispatchers.Main.immediate
    )
}
