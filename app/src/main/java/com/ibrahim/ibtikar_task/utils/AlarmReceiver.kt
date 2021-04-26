package com.ibrahim.ibtikar_task.utils

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.ibrahim.ibtikar_task.R
import com.ibrahim.ibtikar_task.notes.presentation.view.activity.MainActivity

const val EXTRA_NOTE_TITLE = "note title"
const val EXTRA_NOTE_BODY = "note body"
const val EXTRA_NOTE_ID = "note id"

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Log.d("TAG", "onReceive: onReceive called")

        showNotification(
                context,
                intent.getStringExtra(EXTRA_NOTE_TITLE),
                intent.getStringExtra(EXTRA_NOTE_BODY),
                intent.getIntExtra(EXTRA_NOTE_ID, 0)
        )

    }

    private fun showNotification(context: Context, noteTitle: String?, noteBody: String?, noteID: Int) {
        val notifyIntent = Intent(context, MainActivity::class.java)
        val notifyPendingIntent =
                PendingIntent.getActivity(
                        context,
                        noteID,
                        notifyIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                )

        var builder = NotificationCompat.Builder(context, "CHANNEL_ID")
                .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
                .setContentTitle(noteTitle)
                .setContentText(noteBody)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(notifyPendingIntent)
                .setStyle(
                        NotificationCompat.BigTextStyle()
                                .bigText(noteBody)
                )

        NotificationManagerCompat.from(context)
                .notify(noteID, builder.build())
    }


}