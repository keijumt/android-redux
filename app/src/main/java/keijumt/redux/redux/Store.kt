package keijumt.redux.redux

import keijumt.redux.redux.middleware.Middleware
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.launch

class Store(
    private val reducer: Reducer,
    initialState: AppState = AppState()
) {

    private val job = Job()
    private val coroutineScope = CoroutineScope(job + Dispatchers.Main)

    private val _state: ConflatedBroadcastChannel<AppState> =
        ConflatedBroadcastChannel(initialState)

    private val middlewares = mutableListOf<Middleware>()

    fun dispatch(action: Action) = coroutineScope.launch {
        val currentState = _state.value
        var newAction = action

        middlewares.forEach { middleware ->
            newAction = middleware.before(newAction, currentState)
        }

        val newState = reducer.reduce(
            state = currentState,
            action = newAction
        )
        _state.send(newState)

        middlewares.forEach { middleWare ->
            middleWare.after(newAction, newState)
        }
    }

    fun subscribe(): Flow<AppState> = _state.asFlow()

    fun addMiddleware(middleware: Middleware) {
        middlewares.add(middleware)
    }

    fun removeMiddleware(middleware: Middleware) {
        middlewares.remove(middleware)
    }
}