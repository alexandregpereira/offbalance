package br.alexandregpereira.offbalance.state

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * State holders are single use: [onCleared] cancels [scope] for good, so they must be registered
 * with `factory { }`, never `single { }`, and are cleared when the screen leaves the composition.
 */
abstract class StateHolder<State : Any>(
    initialState: State,
) {

    private var isCleared = false

    protected val scope: CoroutineScope = createScope()
        get() {
            check(!isCleared) {
                "${this::class.simpleName} was already cleared. State holders are single use: " +
                    "register them with factory { }, never single { }."
            }
            return field
        }

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<State> = _state.asStateFlow()

    protected fun setState(block: State.() -> State) {
        _state.value = _state.value.block()
    }

    open fun onCleared() {
        scope.cancel()
        isCleared = true
    }

    private fun createScope(): CoroutineScope = CoroutineScope(
        SupervisorJob() + Dispatchers.Main.immediate
    )
}
