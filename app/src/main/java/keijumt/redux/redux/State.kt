package keijumt.redux.redux

import keijumt.redux.model.Repo

data class State(
    val repos: List<Repo> = emptyList()
)