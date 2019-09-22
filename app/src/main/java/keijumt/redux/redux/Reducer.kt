package keijumt.redux.redux

class Reducer {
    fun reduce(state: AppState, action: Action): AppState {
        return when (action) {
            is AppAction.SearchReposAction.SearchLoading -> {
                state.copy(
                    searchRepoState = state.searchRepoState.copy(
                        isLoading = action.isLoading
                    )
                )
            }
            is AppAction.SearchReposAction.SearchRepos -> state.copy(
                searchRepoState = state.searchRepoState.copy(
                    repos = action.repos
                )
            )
            else -> throw IllegalArgumentException("unknown action $action")
        }
    }
}