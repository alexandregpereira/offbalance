package br.alexandregpereira.offbalance.state

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

/**
 * The point of clearing a state holder is that it stops observing. That is invisible from the UI,
 * so it is asserted here instead.
 */
class StateHolderTest {

    private val dispatcher = StandardTestDispatcher()

    /** Subscribes on init, the way every feature state holder listens to SyncEventManager. */
    private class ObservingStateHolder(
        events: MutableSharedFlow<Unit>,
    ) : StateHolder<Int>(0) {

        init {
            events.onEach { setState { this + 1 } }.launchIn(scope)
        }

        fun launchOnScope() {
            scope.launch { }
        }
    }

    @BeforeTest
    fun setUp() = Dispatchers.setMain(dispatcher)

    @AfterTest
    fun tearDown() = Dispatchers.resetMain()

    @Test
    fun `observes events while it is alive`() = runTest(dispatcher) {
        val events = MutableSharedFlow<Unit>(extraBufferCapacity = 1)
        val stateHolder = ObservingStateHolder(events)
        runCurrent()

        events.emit(Unit)
        events.emit(Unit)
        runCurrent()

        assertEquals(2, stateHolder.state.value)
    }

    @Test
    fun `stops observing events once cleared`() = runTest(dispatcher) {
        val events = MutableSharedFlow<Unit>(extraBufferCapacity = 1)
        val stateHolder = ObservingStateHolder(events)
        runCurrent()

        events.emit(Unit)
        runCurrent()
        stateHolder.onCleared()

        events.emit(Unit)
        events.emit(Unit)
        runCurrent()

        assertEquals(1, stateHolder.state.value)
    }

    @Test
    fun `refuses to be used after being cleared`() = runTest(dispatcher) {
        val stateHolder = ObservingStateHolder(MutableSharedFlow())
        runCurrent()
        stateHolder.onCleared()

        val error = assertFailsWith<IllegalStateException> { stateHolder.launchOnScope() }

        assertEquals(
            "ObservingStateHolder was already cleared. State holders are single use: " +
                "register them with factory { }, never single { }.",
            error.message,
        )
    }
}
