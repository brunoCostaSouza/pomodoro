package com.example.pomodoro.presenters.presenter

import com.example.pomodoro.model.Pomodoro
import com.example.pomodoro.presenters.contract.HistoryPomodoroContract
import com.example.pomodoro.respository.IPomodoroRepository
import com.example.pomodoro.util.PomodoroCallback
import com.example.pomodoro.util.getFormattedDate
import com.example.pomodoro.util.toCalendar
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.*

class HistoryPomodoroPresenter(
    val mView: HistoryPomodoroContract.View
) : HistoryPomodoroContract.Presenter, KoinComponent {

    private val mRepository: IPomodoroRepository by inject()

    override fun listAllPomodoro() {
        mRepository.listAllPomodoro(object : PomodoroCallback {
            override fun listAllWithSuccess(list: List<Pomodoro>) {
                mView.showAllPomodoro(separatePomodoro(list))
            }
        })
    }

    private fun separatePomodoro(currentList: List<Pomodoro>): List<Pomodoro> {
        val list = mutableListOf<Pomodoro>()
        currentList.forEach { it1 ->

            if (list.contains(it1)) {
                return@forEach
            }

            list.add(Pomodoro(viewType = 2, description = it1.getFormattedDate()))
            list.add(it1)

            currentList.forEach { it2 ->
                if (it1.whenRun.toCalendar().get(Calendar.DATE) == it2.whenRun.toCalendar().get(Calendar.DATE) && !list.contains(it2)) {
                    list.add(it2)
                }
            }
        }

        return list
    }
}