package com.example.pomodoro.view.fragments

import android.graphics.Color
import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.pomodoro.R
import com.example.pomodoro.model.PState
import com.example.pomodoro.presenters.contract.NewPomodoroContract
import com.example.pomodoro.presenters.presenter.NewPomodoroPresenter
import com.example.pomodoro.util.Constants
import com.example.pomodoro.util.formatToPomodoro
import com.example.pomodoro.util.toMinutes
import com.example.pomodoro.util.toSeconds
import kotlinx.android.synthetic.main.fragment_new.*
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.get
import java.text.DecimalFormat


class FragmentNew : Fragment(), NewPomodoroContract.View {

    private var pomodoroTimer = PomodoroTimer(Constants.TIME_ALL)

    private val mPresenter: NewPomodoroContract.Presenter = get(
        NewPomodoroPresenter::class.java, parameters = { parametersOf(this)}
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        btn_play_pause.setOnClickListener {
            mPresenter.startStopPomodoroTimer()
        }
    }

    override fun startTimer() {
        pomodoroTimer.start()
        text_timer.setTextColor(Color.RED)
        btn_play_pause.setImageResource(R.drawable.ic_pause)
        animation_view.playAnimation()
    }

    override fun restartTimer() {
        pomodoroTimer.cancel()
        text_timer.text = getString(R.string._25_00)
        text_timer.setTextColor(ContextCompat.getColor(context!!, R.color.gray))
        btn_play_pause.setImageResource(R.drawable.ic_play)
        animation_view.pauseAnimation()
    }

    override fun playAlert() {
        val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        RingtoneManager.getRingtone(context, notification).play()
        showToast("Finished Pomodoro")
        animation_view.pauseAnimation()
    }

    override fun showSuccess(message: String) {
        showToast(message)
    }

    override fun showError(message: String) {
        showError(message)
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentNew()
    }

    inner class PomodoroTimer(millis: Long) : CountDownTimer(millis, 1) {
        override fun onFinish() {
            mPresenter.statePomodoroToFinish()
            mPresenter.finishPomodoro()
        }

        override fun onTick(millis: Long) {
            mPresenter.updateTimePomodoro(millis)
            text_timer.text = DecimalFormat("00").formatToPomodoro(millis.toMinutes(), millis.toSeconds())
        }
    }
}