package keijumt.redux.redux

class Reducer {
    fun reduce(state: State, action: Action): State {
        return when (action) {
            is Action.RefreshRepos -> state.copy(repos = action.repos)
        }
    }
}