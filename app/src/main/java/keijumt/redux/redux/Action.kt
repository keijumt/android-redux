package keijumt.redux.redux

import keijumt.redux.model.Repo

sealed class Action {
    data class RefreshRepos(val repos: List<Repo>) : Action()
}
