package keijumt.redux.redux.actioncreator

import keijumt.redux.data.repository.RepoRepository
import keijumt.redux.redux.Action
import keijumt.redux.redux.AppAction
import keijumt.redux.redux.AsyncAction
import keijumt.redux.redux.Dispatcher

class RepoActionCreator(
    private val repoRepository: RepoRepository
) {
    fun searchRepo(q: String) = object : AsyncAction {
        override suspend fun execute(dispatcher: Dispatcher): Action {
            return try {
                dispatcher.dispatch(AppAction.SearchReposAction.SearchLoading(true))
                AppAction.SearchReposAction.SearchRepos(repoRepository.searchByRepoName(q))
            } catch (e: Exception) {
                AppAction.SearchReposAction.SearchRepos(emptyList())
            } finally {
                dispatcher.dispatch(AppAction.SearchReposAction.SearchLoading(false))
            }
        }
    }
}