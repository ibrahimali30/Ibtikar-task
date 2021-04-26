package com.ibrahim.ibtikar_task.notes.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Note(
    var title: String,
    var body: String,
    var dateString: String,
    var time: Long,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
): Serializable {
    fun getReminderNotificationTime(): Long {
        return time - 10*60*1000 //10 mins earlier
    }
}