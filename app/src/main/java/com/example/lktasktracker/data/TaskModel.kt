package com.example.lktasktracker.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID

@Entity(tableName = "tasks_data_table")
data class TaskModel(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "expiration_date")
    var expirationDate: Date,
    @ColumnInfo(name = "is_done")
    var isDone: Boolean = false
)
