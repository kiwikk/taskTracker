package com.example.lktasktracker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [TaskModel::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class TaskDB : RoomDatabase() {
    abstract fun taskDao(): TaskDAO

    companion object {
        @Volatile
        private var INSTANCE: TaskDB? = null

        fun getDatabase(context: Context): TaskDB {
            val tmp = INSTANCE
            if(tmp != null) {
                return tmp
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDB::class.java,
                    "task_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}