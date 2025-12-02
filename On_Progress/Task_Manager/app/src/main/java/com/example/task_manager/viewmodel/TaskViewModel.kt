package com.example.task_manager.viewmodel

import androidx.lifecycle.*
import com.example.task_manager.data.Task
import com.example.task_manager.data.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {

    // LiveData from Flow, automatically updates when DB changes
    val tasks: LiveData<List<Task>> = repository.getAllTasks().asLiveData()

    // Add a new task
    fun addTask(title: String, description: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(Task(taskTitle = title, taskDescription = description))
        }
    }

    // Update an existing task
    fun updateTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(task)
        }
    }

    // Delete a task
    fun deleteTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(task)
        }
    }

    // Factory for ViewModelProvider
    class Factory(private val repository: TaskRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
                return TaskViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
