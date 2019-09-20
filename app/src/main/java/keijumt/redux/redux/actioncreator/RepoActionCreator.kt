package keijumt.redux.redux.actioncreator

import keijumt.redux.model.Repo
import keijumt.redux.redux.Action
import kotlin.random.Random

class RepoActionCreator {

    fun loadRepo(): Action {
        return Action.RefreshRepos(repos = (0 until 30).map {
            Repo(
                id = it.toLong(),
                name = "Repository ${Random.nextInt(100)}"
            )
        })
    }
}