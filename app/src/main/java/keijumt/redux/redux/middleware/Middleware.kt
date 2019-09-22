package keijumt.redux.redux.middleware

import keijumt.redux.redux.Action
import keijumt.redux.redux.AppState

interface Middleware {
    suspend fun before(action: Action, state: AppState): Action
    suspend fun after(action: Action, state: AppState): Action
}