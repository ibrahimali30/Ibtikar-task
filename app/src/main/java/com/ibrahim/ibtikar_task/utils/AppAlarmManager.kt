package com.ibrahim.ibtikar_task.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.ibrahim.ibtikar_task.notes.data.model.Note
import javax.inject.Inject


class AppAlarmManager @Inject constructor(val context: Context) {

    fun setAlarm(note: Note) {
        val myIntent = Intent(context, AlarmReceiver::class.java)
        myIntent.putExtra(EXTRA_NOTE_TITLE, note.title)
        myIntent.putExtra(EXTRA_NOTE_BODY, note.body)
        myIntent.putExtra(EXTRA_NOTE_ID, note.id)

        val pendingIntent = PendingIntent.getBroadcast(context, note.id, myIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, note.getReminderNotificationTime(),  pendingIntent)
    }

    fun cancelAlarm(note: Note?){
        note ?: return
        val myIntent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, note.id, myIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        alarmManager.cancel(pendingIntent)

    }

}


