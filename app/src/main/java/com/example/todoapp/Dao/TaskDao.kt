package com.example.todoapp.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todoapp.Model.Task

@Dao
interface TaskDao {
    @Insert
    fun inserttask(task: Task)
    @Update
    fun updatetask(task: Task)
    @Delete
    fun deletetask(task: Task)
    @Query("select * from tasks")
    fun getalltasks():List<Task>


}