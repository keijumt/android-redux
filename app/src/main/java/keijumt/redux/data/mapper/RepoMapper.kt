package keijumt.redux.data.mapper

import keijumt.redux.data.response.OwnerResponse
import keijumt.redux.data.response.RepoResponse
import keijumt.redux.model.Owner
import keijumt.redux.model.Repo

fun List<RepoResponse>.toRepos(): List<Repo> {
    return map {
        Repo(
            id = it.id,
            name = it.name,
            fullName = it.fullName,
            description = it.description,
            owner = it.owner.toOwner(),
            stars = it.stars
        )
    }
}

private fun OwnerResponse.toOwner(): Owner {
    return Owner(
        userId = userId,
        url = url,
        avatarUrl = avatarUrl
    )
}