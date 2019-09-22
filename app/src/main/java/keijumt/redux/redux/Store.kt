package keijumt.redux.redux

import keijumt.redux.redux.middleware.Middleware
import keijumt.redux.redux.middleware.AsyncMiddleware
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class Store(
    private val reducer: Reducer,
    thunkMiddleware: AsyncMiddleware,
    initialState: State = State()
) {

    private val job = Job()
    private val coroutineScope = CoroutineScope(job + Dispatchers.Main)

    private val actionStream = BroadcastChannel<Action>(capacity = Channel.BUFFERED)
    private val _state: ConflatedBroadcastChannel<State> =
        ConflatedBroadcastChannel(initialState)

    private val middlewares = mutableListOf<Middleware>(thunkMiddleware)

    init {
        coroutineScope.launch(Dispatchers.IO) {
            actionStream.asFlow()
                .map {
                    var action = it
                    middlewares.forEach { middleware ->
                        action = middleware.before(action, _state.value)
                    }
                    return@map action
                }
                .map {
                    val state = reducer.reduce(
                        state = _state.value,
                        action = it
                    )
                    _state.send(state)
                    return@map it
                }
                .map {
                    var action = it
                    middlewares.forEach { middleWare ->
                        action = middleWare.before(action, _state.value)
                    }
                    return@map action
                }
                .collect()
        }
    }

    fun dispatch(action: Action) = coroutineScope.launch {
        actionStream.send(action)
    }

    fun subscribe(): Flow<State> = _state.asFlow()
}