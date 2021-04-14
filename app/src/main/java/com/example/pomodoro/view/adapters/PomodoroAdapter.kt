package com.example.pomodoro.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pomodoro.R
import com.example.pomodoro.model.Pomodoro
import com.example.pomodoro.util.formatToPomodoro
import com.example.pomodoro.util.formatWhenRun
import com.example.pomodoro.util.toMinutes
import com.example.pomodoro.util.toSeconds
import kotlinx.android.synthetic.main.item_pomodoro.view.*
import kotlinx.android.synthetic.main.separate_item.view.*
import java.text.DecimalFormat

class PomodoroAdapter(private val context: Context) : RecyclerView.Adapter<PomodoroViewHolder>() {
    private var listPomodoro = mutableListOf<Pomodoro>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PomodoroViewHolder {
        return when(viewType) {
            1 -> PomodoroViewHolder(LayoutInflater.from(context).inflate(R.layout.item_pomodoro, parent, false))
            else -> PomodoroViewHolder(LayoutInflater.from(context).inflate(R.layout.separate_item, parent, false))
        }
    }

    override fun getItemCount(): Int {
        return listPomodoro.size
    }

    override fun onBindViewHolder(holder: PomodoroViewHolder, position: Int) {
        holder.bind(listPomodoro[position])
    }

    override fun getItemViewType(position: Int): Int {
        return listPomodoro[position].viewType
    }

    fun updateLis(listPomodoro: List<Pomodoro>) {
        this.listPomodoro.clear()
        this.listPomodoro.addAll(listPomodoro)
    }
}

class PomodoroViewHolder(
        itemView: View
) : RecyclerView.ViewHolder(itemView) {
    fun bind(pomodoro: Pomodoro) {

        if (pomodoro.viewType == 1) {
            itemView.apply {
                text_timer.text = DecimalFormat("00").formatToPomodoro(
                    pomodoro.time.toMinutes(),
                    pomodoro.time.toSeconds()
                )
                text_state.text = pomodoro.state
                text_when_run.text = pomodoro.whenRun.formatWhenRun()
            }
        } else {
            itemView.text_separate.text = pomodoro.description
        }
    }
}