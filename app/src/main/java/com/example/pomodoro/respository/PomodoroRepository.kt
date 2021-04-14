package com.example.pomodoro.respository

import com.example.pomodoro.model.Pomodoro
import com.example.pomodoro.util.PomodoroCallback
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.Sort
import io.realm.kotlin.where
import io.realm.rx.RealmObservableFactory
import java.lang.Exception

class PomodoroRepository(
    private val realm: Realm
) : IPomodoroRepository {

    override fun savePomodoro(pomodoro: Pomodoro, callback: PomodoroCallback) {
        realm.executeTransactionAsync(
            {
                val currentId = it.where(Pomodoro::class.java).max("id")
                pomodoro.id = if (currentId == null) {
                    1
                } else {
                    currentId.toInt() + 1
                }

                it.insertOrUpdate(pomodoro)
            },
            { callback.onSuccess() },
            { callback.onError(Exception(it.message)) })
    }

    override fun listAllPomodoro(callback: PomodoroCallback) {
        val list = mutableListOf<Pomodoro>()
        val result = realm.where(Pomodoro::class.java)
            .sort("whenRun", Sort.DESCENDING)
            .findAll()

        result.forEach { p ->
            list.add(Pomodoro(id = p.id, time = p.time, state = p.state, whenRun = p.whenRun))
        }
        callback.listAllWithSuccess(list)
    }

    companion object {
        fun init(): IPomodoroRepository {
            val config = RealmConfiguration
                .Builder()
                .name("pomodoroDb")
                .build()
            return PomodoroRepository(Realm.getInstance(config))
        }
    }
}