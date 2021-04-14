package com.example.pomodoro.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pomodoro.R
import com.example.pomodoro.model.Pomodoro
import com.example.pomodoro.presenters.contract.HistoryPomodoroContract
import com.example.pomodoro.presenters.presenter.HistoryPomodoroPresenter
import com.example.pomodoro.view.adapters.PomodoroAdapter
import kotlinx.android.synthetic.main.fragment_history.*
import org.koin.java.KoinJavaComponent.get
import org.koin.core.parameter.parametersOf

class FragmentHistory : Fragment(), HistoryPomodoroContract.View {

    private lateinit var mAdapter: PomodoroAdapter
    private lateinit var mLayoutManager: LinearLayoutManager

    private val mPresenter: HistoryPomodoroContract.Presenter = get(
        HistoryPomodoroPresenter::class.java, parameters = { parametersOf(this) }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    override fun onResume() {
        super.onResume()
        getLisPomodoro()
    }

    private fun setupView() {
        this.mAdapter = PomodoroAdapter(context!!)
        list_pomodoro.apply {
            mLayoutManager = LinearLayoutManager(context!!)
            layoutManager = mLayoutManager
            layoutAnimation = AnimationUtils.loadLayoutAnimation(context, R.anim.animation_item)
            adapter = mAdapter
        }
    }

    private fun getLisPomodoro() {
        mPresenter.listAllPomodoro()
    }

    override fun showAllPomodoro(list: List<Pomodoro>) {
        activity?.runOnUiThread {
            if (list.isEmpty()) {
                text_empty.visibility = View.VISIBLE
            } else {
                text_empty.visibility = View.GONE
            }
            mAdapter.updateLis(list)
            mAdapter.notifyDataSetChanged()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentHistory()
    }
}