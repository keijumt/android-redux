package keijumt.redux.redux

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.launch

class Store(
    private val reducer: Reducer,
    initialState: State = State()
) {

    private val job = Job()
    private val coroutineScope = CoroutineScope(job + Dispatchers.Main)

    private val actionStream = BroadcastChannel<Action>(capacity = Channel.BUFFERED)
    private val _state: ConflatedBroadcastChannel<State> =
        ConflatedBroadcastChannel(initialState)

    init {
        coroutineScope.launch(Dispatchers.IO) {
            actionStream.consumeEach {
                val newState = reducer.reduce(
                    state = _state.value,
                    action = it
                )
                _state.send(newState)
            }
        }
    }

    fun dispatch(action: Action) = coroutineScope.launch {
        actionStream.send(action)
    }

    fun subscribe(): Flow<State> = _state.asFlow()
}