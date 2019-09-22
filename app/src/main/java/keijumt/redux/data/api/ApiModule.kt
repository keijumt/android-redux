package keijumt.redux.data.api

import keijumt.redux.data.api.GithubApiFactory
import org.koin.dsl.module

val apiModule = module {
    single { GithubApiFactory.create() }
}