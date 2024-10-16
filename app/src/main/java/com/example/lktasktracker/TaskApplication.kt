package com.example.lktasktracker

import android.app.Application
import com.example.lktasktracker.di.ApplicationComponent
import com.example.lktasktracker.di.DaggerApplicationComponent

class TaskApplication: Application() {
    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.factory().create(this)
    }
}