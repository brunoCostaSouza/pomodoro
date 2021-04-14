package com.example.pomodoro.arch

import com.example.pomodoro.presenters.contract.HistoryPomodoroContract
import com.example.pomodoro.presenters.contract.NewPomodoroContract
import com.example.pomodoro.presenters.presenter.HistoryPomodoroPresenter
import com.example.pomodoro.presenters.presenter.NewPomodoroPresenter
import com.example.pomodoro.respository.PomodoroRepository
import org.koin.dsl.module

val repositoryModules = module {
    single { PomodoroRepository.init() }
}

val presenterModule = module {
    factory { (view: NewPomodoroContract.View) -> NewPomodoroPresenter(view) }
    factory { (view: HistoryPomodoroContract.View) -> HistoryPomodoroPresenter(view) }
}