package com.example.lktasktracker.ui.fragments.todo

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.lktasktracker.data.TaskDB
import com.example.lktasktracker.data.TaskModel
import com.example.lktasktracker.data.TaskRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ToDoViewModel(application: Application) : ViewModel() {
    private val repository: TaskRepository
    private val _toDoTasks = MutableStateFlow<List<TaskModel>>(emptyList())
    val toDoTasks = _toDoTasks.asStateFlow()

    init {
        val db = TaskDB.getDatabase(application).taskDao()
        repository = TaskRepository(db)
        viewModelScope.launch {
            repository.getToDoTasks().collect {
                _toDoTasks.value = it
            }
        }
    }

    fun addTask(task: TaskModel) {
        viewModelScope.launch {
            repository.insert(task)
            Log.i(TAG, "inserted: $task")
        }
    }

    fun markTaskAsDone(task: TaskModel) {
        viewModelScope.launch {
            repository.update(task.copy(isDone = true))
        }
    }

    companion object {
        private val TAG = ToDoViewModel::class.java.simpleName

        val APPLICATION_KEY = object : CreationExtras.Key<Application> {}
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val application =
                    extras[APPLICATION_KEY] as Application
                return ToDoViewModel(application) as T
            }
        }
    }
}