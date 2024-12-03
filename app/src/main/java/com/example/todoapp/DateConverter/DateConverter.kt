package com.example.todoapp.DateConverter

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {
    @TypeConverter
    fun fromdate(date : Date): Long {
        return date.time
    }
    @TypeConverter
    fun toDate(date : Long): Date {
        return Date(date)
    }
}