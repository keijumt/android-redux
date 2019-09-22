package keijumt.redux.data.repository

import org.koin.dsl.module

val repositoryModule = module {
    single<RepoRepository> { RepoRepositoryImpl(get()) }
}