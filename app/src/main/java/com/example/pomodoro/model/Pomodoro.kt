package com.example.pomodoro.model

import io.realm.RealmObject
import io.realm.annotations.Ignore
import io.realm.annotations.PrimaryKey
import java.util.*

open class Pomodoro (
    @PrimaryKey
    open var id: Int? = null,
    open var time: Long = 0,
    open var state: String = PState.EXECUTION.name,
    open var whenRun: Long = Calendar.getInstance().time.time,
    @Ignore
    var viewType: Int = 1,
    @Ignore
    var description: String = ""
): RealmObject() {
    fun reset() {
        time = 0
        state = PState.EXECUTION.name
        whenRun = Calendar.getInstance().time.time
    }
}

enum class PState {
    EXECUTION,
    STOPPED,
    FINISHED
}