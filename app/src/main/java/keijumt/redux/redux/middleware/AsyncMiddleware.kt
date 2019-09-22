package keijumt.redux.redux.middleware

import keijumt.redux.redux.Action
import keijumt.redux.redux.AsyncAction
import keijumt.redux.redux.State

class AsyncMiddleware : Middleware {
    override suspend fun before(action: Action, state: State): Action {
        return if (action is AsyncAction) {
            action.execute()
        } else {
            action
        }
    }

    override suspend fun after(action: Action, state: State): Action {
        return action
    }
}