package keijumt.redux.data.repository

import keijumt.redux.data.api.GithubApi
import keijumt.redux.data.mapper.toRepoDetail
import keijumt.redux.data.mapper.toRepos
import keijumt.redux.model.Repo
import keijumt.redux.model.RepoDetail

interface RepoRepository {
    suspend fun getRepo(ownerName: String, repoName: String): RepoDetail
    suspend fun searchByRepoName(q: String): List<Repo>
}

internal class RepoRepositoryImpl(
    private val api: GithubApi
) : RepoRepository {
    override suspend fun getRepo(ownerName: String, repoName: String): RepoDetail {
        return try {
            api.getRepo(ownerName, repoName).toRepoDetail()
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun searchByRepoName(q: String): List<Repo> {
        return try {
            api.searchReposByRepoName(q).items.toRepos()
        } catch (e: Exception) {
            throw e
        }
    }
}