package keijumt.redux.redux.actioncreator

import keijumt.redux.model.Repo
import keijumt.redux.redux.Action
import keijumt.redux.redux.AppAction
import keijumt.redux.redux.AsyncAction
import kotlin.random.Random

// TODO di repository
class RepoActionCreator {
    fun loadRepo() = object : AsyncAction {
        override suspend fun execute(): Action {
            return AppAction.RefreshRepos(repos = (0 until 30).map {
                Repo(
                    id = it.toLong(),
                    name = "Repository ${Random.nextInt(100)}"
                )
            })
        }
    }
}