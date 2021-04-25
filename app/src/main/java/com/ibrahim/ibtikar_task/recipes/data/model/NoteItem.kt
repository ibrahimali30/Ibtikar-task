package com.ibrahim.ibtikar_task.recipes.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class NoteItem(
    @PrimaryKey
    val id: String = ""
): Serializable