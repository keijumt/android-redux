package keijumt.redux.data.repository

import keijumt.redux.data.api.GithubApi
import keijumt.redux.data.mapper.toRepos
import keijumt.redux.model.Repo

interface RepoRepository {
    suspend fun searchByRepoName(q: String): List<Repo>
}

internal class RepoRepositoryImpl(
    private val api: GithubApi
) : RepoRepository {
    override suspend fun searchByRepoName(q: String): List<Repo> {
        return try {
            api.searchReposByRepoName(q).items.toRepos()
        } catch (e: Exception) {
            throw e
        }
    }
}