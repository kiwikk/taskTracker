package com.example.di

import android.content.Context
import com.example.lktasktracker.data.TaskDB
import com.example.lktasktracker.data.TaskRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideRepository(context: Context): TaskRepository {
        return TaskRepository(TaskDB.getDatabase(context).taskDao())
    }
}