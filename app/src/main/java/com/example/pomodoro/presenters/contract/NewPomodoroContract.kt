package com.example.pomodoro.presenters.contract

interface NewPomodoroContract {

    interface View {
        fun playAlert()
        fun startTimer()
        fun restartTimer()
        fun showError(message: String)
        fun showSuccess(message: String)
    }
    interface Presenter {
        fun startStopPomodoroTimer()
        fun statePomodoroToFinish()
        fun finishPomodoro()
        fun updateTimePomodoro(millis: Long)
    }
}