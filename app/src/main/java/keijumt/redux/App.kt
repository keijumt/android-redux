package keijumt.redux

import android.app.Application
import keijumt.redux.data.api.apiModule
import keijumt.redux.data.repository.repositoryModule
import keijumt.redux.di.appModule
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(appModule, apiModule, repositoryModule))
        }
    }
}