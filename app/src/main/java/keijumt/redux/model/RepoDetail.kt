package keijumt.redux.model

data class RepoDetail(
    val id: Int,
    val name: String,
    val fullName: String,
    val description: String?,
    val owner: Owner,
    val stars: Int
)