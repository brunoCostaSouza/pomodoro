package com.example.pomodoro.arch

import android.app.Application
import io.realm.Realm
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

class MyApplication: Application(), KoinComponent {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        startKoin {
            modules(listOf(presenterModule, repositoryModules))
        }
    }
}