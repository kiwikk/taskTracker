package com.example.lktasktracker.data

import java.util.Date
import java.util.UUID

data class TaskModel(
    val id: UUID = UUID.randomUUID(),
    var title: String,
    var description: String,
    var expirationDate: Date,
    var isDone: Boolean = false
)
