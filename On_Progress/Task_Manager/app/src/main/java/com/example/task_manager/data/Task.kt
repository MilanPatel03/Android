package com.example.task_manager.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task( @PrimaryKey(autoGenerate = true) val taskId: Int = 0,
                 val taskTitle: String,
                 val taskDescription: String? = null,
                 val isStarred: Boolean = false,
                 val isCompleted: Boolean = false
)
