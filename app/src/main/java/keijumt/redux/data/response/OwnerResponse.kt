package keijumt.redux.data.response

import com.squareup.moshi.Json

data class OwnerResponse(
    @Json(name = "login")
    val userId: String,
    val url: String?,
    @Json(name = "avatar_url")
    val avatarUrl: String
)