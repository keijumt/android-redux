package keijumt.redux.data.response

import com.squareup.moshi.Json

data class RepoResponse(
    val id: Int,
    val name: String,
    @Json(name = "full_name")
    val fullName: String,
    val description: String?,
    val owner: OwnerResponse,
    @Json(name = "stargazers_count")
    val stars: Int
)