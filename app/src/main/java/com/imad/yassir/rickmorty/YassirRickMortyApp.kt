package com.imad.yassir.rickmorty

import android.app.Application
import com.imad.yassir.rickmorty.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class YassirRickMortyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@YassirRickMortyApp)
            androidLogger()

            modules(appModule)
        }
    }
}
