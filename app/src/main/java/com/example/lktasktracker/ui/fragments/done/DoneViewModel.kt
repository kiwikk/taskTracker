package com.example.lktasktracker.ui.fragments.done

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lktasktracker.data.TaskModel
import com.example.lktasktracker.data.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class DoneViewModel @Inject constructor(repository: TaskRepository) : ViewModel() {
    private val _doneTasks = MutableStateFlow<List<TaskModel>>(emptyList())
    val doneTasks = _doneTasks.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getCompletedTasks().collect {
                _doneTasks.value = it
                Log.i(TAG, "new task $it")

            }
        }
        Log.i(TAG, "init")
    }

    companion object {
        private val TAG = DoneViewModel::class.java.simpleName

//        val APPLICATION_KEY = object : CreationExtras.Key<Application> {}
//        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
//            @Suppress("UNCHECKED_CAST")
//            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
//                val application =
//                    extras[APPLICATION_KEY] as Application
//                return DoneViewModel(application) as T
//            }
//        }
    }
}