package com.ibrahim.ibtikar_task.utils

import com.ibrahim.ibtikar_task.notes.data.model.Note


fun generateNoteEntity(id: Int = 1): Note {
    return Note("title", "body", "date", 1234234, id)
}