package com.example.pomodoro.respository

import com.example.pomodoro.model.Pomodoro
import com.example.pomodoro.util.PomodoroCallback

interface IPomodoroRepository {
    fun savePomodoro(pomodoro: Pomodoro, callback: PomodoroCallback)
    fun listAllPomodoro(callback: PomodoroCallback)
}