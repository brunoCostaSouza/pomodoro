package com.example.pomodoro.presenters.presenter

import com.example.pomodoro.model.PState
import com.example.pomodoro.model.Pomodoro
import com.example.pomodoro.presenters.contract.NewPomodoroContract
import com.example.pomodoro.respository.IPomodoroRepository
import com.example.pomodoro.util.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.*


class NewPomodoroPresenter(
    private val mView: NewPomodoroContract.View
) : NewPomodoroContract.Presenter, KoinComponent {

    private val mRepository: IPomodoroRepository by inject()

    private var timeIsRunning = false
    private var pomodoroObject = Pomodoro()

    override fun startStopPomodoroTimer() {
        timeIsRunning = if (timeIsRunning) {
            pomodoroObject.state = PState.STOPPED.name
            finishPomodoro()
            false
        } else {
            pomodoroObject.let {
                it.state = PState.EXECUTION.name
                it.whenRun = Calendar.getInstance().timeInMillis
            }
            mView.startTimer()
            true
        }
    }

    override fun statePomodoroToFinish() {
        pomodoroObject.state = PState.FINISHED.name
    }

    override fun updateTimePomodoro(millis: Long) {
        pomodoroObject.time = Constants.TIME_ALL - millis
    }

    override fun finishPomodoro() {
        mRepository.savePomodoro(pomodoroObject, object : PomodoroCallback {
            override fun onSuccess() {
                pomodoroObject.reset()
                mView.restartTimer()
                mView.playAlert()
                mView.showSuccess("Pomodoro saved successfully")
            }

            override fun onError(ignore: Exception?) {
                mView.showError("Failed to save pomodoro")
            }
        })
    }
}