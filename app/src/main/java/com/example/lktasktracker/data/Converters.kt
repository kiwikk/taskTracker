package com.example.lktasktracker.data

import androidx.room.TypeConverter
import java.util.Date

class Converters {
    @TypeConverter
    fun fromTimestamp(value: String?): Date? {
        return value?.let { Date(Date.parse(value)) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date): String {
        return date.toString()
    }
}