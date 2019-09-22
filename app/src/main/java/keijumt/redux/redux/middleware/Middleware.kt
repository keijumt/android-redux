package keijumt.redux.redux.middleware

import keijumt.redux.redux.Action
import keijumt.redux.redux.State

interface Middleware {
    suspend fun before(action: Action, state: State): Action
    suspend fun after(action: Action, state: State): Action
}