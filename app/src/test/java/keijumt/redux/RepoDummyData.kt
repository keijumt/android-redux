package keijumt.redux

import keijumt.redux.model.Owner
import keijumt.redux.model.Repo
import java.util.UUID

fun getReposDummyData(size: Int): List<Repo> = (0 until size).map {
    Repo(
        id = it,
        name = "name $it",
        fullName = "fullname $it",
        description = "description $it",
        owner = getDummyOwner(),
        stars = it
    )
}

private fun getDummyOwner() = Owner(
    userId = UUID.randomUUID().toString(),
    url = "",
    avatarUrl = ""
)