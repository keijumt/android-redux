package keijumt.redux.redux.middleware

import keijumt.redux.redux.Action
import keijumt.redux.redux.AppState
import keijumt.redux.redux.AsyncAction
import keijumt.redux.redux.Dispatcher

class AsyncMiddleware(
    private val dispatcher: Dispatcher
) : Middleware {
    override suspend fun before(action: Action, state: AppState): Action {
        return if (action is AsyncAction) {
            action.execute(dispatcher)
        } else {
            action
        }
    }

    override suspend fun after(action: Action, state: AppState): Action {
        return action
    }
}