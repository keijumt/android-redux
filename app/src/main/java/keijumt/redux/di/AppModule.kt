package keijumt.redux.di

import keijumt.redux.redux.Reducer
import keijumt.redux.redux.Store
import keijumt.redux.redux.actioncreator.RepoActionCreator
import keijumt.redux.redux.middleware.AsyncMiddleware
import org.koin.dsl.module

val appModule = module {
    single { Reducer() }
    single { AsyncMiddleware() }
    single { Store(get(), get()) }
    single { RepoActionCreator(get()) }
}