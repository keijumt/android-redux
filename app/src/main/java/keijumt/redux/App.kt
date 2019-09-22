package keijumt.redux

import android.app.Application
import keijumt.redux.data.api.apiModule
import keijumt.redux.data.repository.repositoryModule
import keijumt.redux.di.appModule
import keijumt.redux.redux.Dispatcher
import keijumt.redux.redux.Store
import keijumt.redux.redux.middleware.AsyncMiddleware
import org.koin.android.ext.android.inject
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(appModule, apiModule, repositoryModule))
        }

        val store: Store by inject()
        store.addMiddleware(AsyncMiddleware(Dispatcher(store)))
    }
}