package keijumt.redux.redux

import keijumt.redux.redux.state.SearchRepoState

interface State

data class AppState(
    val searchRepoState: SearchRepoState = SearchRepoState()
) : State