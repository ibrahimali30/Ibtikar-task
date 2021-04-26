package com.ibrahim.ibtikar_task.notes.presentation.view.helper

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.fragment.app.FragmentActivity
import java.util.*

class TimePickerHelper(
        val activity: FragmentActivity?,
        val onTimePicked: (timeLong: Long)-> Unit
) : TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    val calendar = Calendar.getInstance()

    fun show() {
        showDatePicker()
    }

    private fun showDatePicker() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(activity!! , this, year, month, day).show()
    }

    private fun showTimePicker() {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)
        TimePickerDialog(activity, this, hour, minute, false).show()
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)
        onTimePicked(calendar.time.time)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        showTimePicker()
        onTimePicked(calendar.time.time)
    }


}