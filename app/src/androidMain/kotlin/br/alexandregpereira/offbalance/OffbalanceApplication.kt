package br.alexandregpereira.offbalance

import android.app.Application
import br.alexandregpereira.offbalance.di.initKoinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class OffbalanceApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@OffbalanceApplication)
            initKoinModules()
        }
    }
}
