package keijumt.redux.redux

class Reducer {
    fun reduce(state: State, action: Action): State {
        return when (action) {
            is AppAction.RefreshRepos -> state.copy(repos = action.repos)
            else -> throw IllegalArgumentException("unknown action $action")
        }
    }
}