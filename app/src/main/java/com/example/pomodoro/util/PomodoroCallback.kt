package com.example.pomodoro.util

import com.example.pomodoro.model.Pomodoro

interface PomodoroCallback {
    fun onSuccess() {}
    fun onError(ignore: Exception?) {}
    fun listAllWithSuccess(list: List<Pomodoro>){}
}