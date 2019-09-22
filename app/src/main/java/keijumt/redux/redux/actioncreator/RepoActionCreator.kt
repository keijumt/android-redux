package keijumt.redux.redux.actioncreator

import keijumt.redux.data.repository.RepoRepository
import keijumt.redux.redux.Action
import keijumt.redux.redux.AppAction
import keijumt.redux.redux.AsyncAction

class RepoActionCreator(
    private val repoRepository: RepoRepository
) {
    fun searchRepo(q: String) = object : AsyncAction {
        override suspend fun execute(): Action {
            return try {
                AppAction.SearchRepos(repoRepository.searchByRepoName(q))
            } catch (e: Exception) {
                AppAction.SearchRepos(emptyList())
            }
        }
    }
}