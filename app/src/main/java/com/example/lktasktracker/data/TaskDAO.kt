package com.example.lktasktracker.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDAO {
    @Insert
    suspend fun insertTask(task: TaskModel)

    @Update
    suspend fun updateTask(task: TaskModel)

    @Query("SELECT * FROM tasks_data_table WHERE is_done = 0")
    fun getToDoTasks(): Flow<List<TaskModel>>

    @Query("SELECT * FROM tasks_data_table WHERE is_done = 1")
    fun getDoneTasks(): Flow<List<TaskModel>>

    @Delete
    suspend fun deleteTask(task: TaskModel)
}