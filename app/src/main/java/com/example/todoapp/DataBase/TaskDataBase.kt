package com.example.todoapp.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.todoapp.Dao.TaskDao
import com.example.todoapp.DateConverter.DateConverter
import com.example.todoapp.Model.Task

@Database(entities = [Task::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class TaskDataBase : RoomDatabase() {
    abstract fun getdao () : TaskDao
    companion object{
        private var Instance : TaskDataBase? = null
        fun getDatabase (context : Context) : TaskDataBase{
            Instance = Room.databaseBuilder(context,TaskDataBase::class.java,"TaskDataBase")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
            return Instance!!
        }
    }
}