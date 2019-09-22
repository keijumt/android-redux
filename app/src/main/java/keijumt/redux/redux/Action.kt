package keijumt.redux.redux

import keijumt.redux.model.Repo

interface Action

interface AsyncAction : Action {
    suspend fun execute(): Action
}

sealed class AppAction : Action {
    data class SearchRepos(val repos: List<Repo>) : AppAction()
}
