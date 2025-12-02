package com.example.task_manager.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
What features we will add (Google Tasks-like but unique)
✔ Add tasks (title, description, importance/star)

✔ Show tasks in a RecyclerView
✔ Swipe to delete
✔ Edit task on tap
✔ Mark as starred
✔ Filter tabs (All, Starred, Completed)
✔ Use ViewModel + LiveData + Repository (professional architecture)
 **/

class TaskRepository(private val dao: TaskDAO) {
    fun getAllTasks() = dao.getAllTasks()
    suspend fun insert(task: Task) = dao.insertTask(task)
    suspend fun update(task: Task) = dao.insertTask(task)

    suspend fun delete(task: Task) = dao.deleteTask(task)
}