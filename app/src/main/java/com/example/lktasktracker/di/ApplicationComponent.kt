package com.example.lktasktracker.di

import android.content.Context
import com.example.di.RepositoryModule
import com.example.lktasktracker.ui.fragments.VMFactory
import com.example.lktasktracker.ui.fragments.done.DoneFragment
import com.example.lktasktracker.ui.fragments.todo.ToDoFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class, AppModule::class])
interface ApplicationComponent {
    fun inject(obj: ToDoFragment)
    fun inject(obj: DoneFragment)

    fun doneVMFactory(): VMFactory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }
}