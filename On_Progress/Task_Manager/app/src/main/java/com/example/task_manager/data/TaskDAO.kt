package com.example.task_manager.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDAO {

    @Insert
    fun insertTask(task: Task)

    @Update
    fun updateTask(task: Task)

    @Delete
    fun deleteTask(task: Task)


    //Get All Tasks, by
    @Query("SELECT * FROM tasks ORDER BY taskId DESC")
    fun getAllTasks(): Flow<List<Task>>


}