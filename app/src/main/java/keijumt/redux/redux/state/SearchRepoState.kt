package keijumt.redux.redux.state

import keijumt.redux.model.Repo

data class SearchRepoState(
    val isLoading: Boolean = false,
    val repos: List<Repo> = emptyList()
)