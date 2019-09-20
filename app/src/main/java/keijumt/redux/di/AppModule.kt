package keijumt.redux.di

import keijumt.redux.redux.Reducer
import keijumt.redux.redux.Store
import keijumt.redux.redux.actioncreator.RepoActionCreator
import org.koin.dsl.module

val appModule = module {
    single { Reducer() }
    single { Store(get()) }
    single { RepoActionCreator() }
}