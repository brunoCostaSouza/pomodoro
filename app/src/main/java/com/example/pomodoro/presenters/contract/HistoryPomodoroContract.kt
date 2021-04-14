package com.example.pomodoro.presenters.contract

import com.example.pomodoro.model.Pomodoro

interface HistoryPomodoroContract {
    interface View {
        fun showAllPomodoro(list: List<Pomodoro>)
    }
    interface Presenter {
        fun listAllPomodoro()
    }
}