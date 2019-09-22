package keijumt.redux.redux

import keijumt.redux.model.Repo

interface Action

interface AsyncAction : Action {
    suspend fun execute(dispatcher: Dispatcher): Action
}

sealed class AppAction : Action {
    sealed class SearchReposAction : AppAction() {
        data class SearchLoading(val isLoading: Boolean) : SearchReposAction()
        data class SearchRepos(val repos: List<Repo>) : SearchReposAction()
    }
}
