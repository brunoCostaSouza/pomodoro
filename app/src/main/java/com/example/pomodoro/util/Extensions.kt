package com.example.pomodoro.util

import android.text.format.DateFormat
import com.example.pomodoro.model.Pomodoro
import java.text.DecimalFormat
import java.util.*

fun Long.toSeconds(): Long {
    return this / 1000 % 60
}

fun Long.toMinutes(): Long {
    return this / 60000 % 60
}

fun Long.toCalendar(): Calendar {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    return calendar
}

fun Long.formatWhenRun(): String {

    val diferenceInMillis = Calendar.getInstance().timeInMillis - this
    val differenceInMinutes = diferenceInMillis / (60 * 1000)

    return when {
        differenceInMinutes < 60 -> { "${differenceInMinutes}m ago" }
        differenceInMinutes == 60L -> { "1h ago" }
        else -> {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = this
            return DateFormat.format("h:mm aa", calendar.time).toString()
        }
    }
}

fun Pomodoro.getFormattedDate(): String {
    return if (Calendar.getInstance().get(Calendar.DATE) == this.whenRun.toCalendar().get(Calendar.DATE) ) {
        "Today"
    } else if (Calendar.getInstance().get(Calendar.DATE) - this.whenRun.toCalendar().get(Calendar.DATE) == 1 ){
        "Yesterday "
    } else {
        return DateFormat.format("MMMM dd yyyy", this.whenRun.toCalendar()).toString();
    }
}

fun DecimalFormat.formatToPomodoro(minutes: Long, seconds: Long): String {
    return this.format(minutes) + ":" + this.format(seconds)
}

