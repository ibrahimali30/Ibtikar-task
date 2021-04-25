package com.ibrahim.ibtikar_task.notes.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Note(
    @PrimaryKey
    var id: String,
    var title: String,
    var body: String,
    var updated_at: String,
    var created_at: String
): Serializable