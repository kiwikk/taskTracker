package com.example.lktasktracker.data

import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDAO) {
    suspend fun insert(task: TaskModel) = taskDao.insertTask(task)

    suspend fun update(task: TaskModel) = taskDao.updateTask(task)

    suspend fun delete(task: TaskModel) = taskDao.deleteTask(task)

    fun getToDoTasks(): Flow<List<TaskModel>> = taskDao.getToDoTasks()
    fun getCompletedTasks(): Flow<List<TaskModel>> = taskDao.getDoneTasks()
}