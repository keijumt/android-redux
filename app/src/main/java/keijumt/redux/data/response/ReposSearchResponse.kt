package keijumt.redux.data.response

import com.squareup.moshi.Json

data class ReposSearchResponse(
    @Json(name = "total_count")
    val total: Int = 0,
    val items: List<RepoResponse>
)