package com.universe.base.core

import android.app.Application
import com.universe.base.core.di.repositoryModule
import com.universe.base.core.di.viewmodelModule
import com.universe.base.data.prefs.SharedPreference
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        SharedPreference.init(this)
        startKoin {
            androidContext(this@MyApplication)
            modules(
                repositoryModule,
                viewmodelModule
            )
        }
    }
}