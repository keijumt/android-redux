package keijumt.redux.data.api

import keijumt.redux.data.response.RepoDetailResponse
import keijumt.redux.data.response.ReposSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {
    @GET("search/repositories")
    suspend fun searchReposByRepoName(@Query("q") query: String): ReposSearchResponse

    @GET("repos/{owner}/{repo}")
    suspend fun getRepo(@Path("owner") ownerName: String, @Path("repo") repoName: String): RepoDetailResponse
}