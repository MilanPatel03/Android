package com.example.task_manager.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.task_manager.R
import com.example.task_manager.data.Task

class TaskAdapter(
    private var tasks: List<Task>,
    private val onTaskClick: (Task) -> Unit,
    private val onStarClick: (Task) -> Unit
) : RecyclerView.Adapter<TaskViewHolder>() {

    fun updateData(newTasks: List<Task>) {
        tasks = newTasks
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]

        holder.textTitle.text = task.taskTitle
        holder.buttonStar.setImageResource(
            if (task.isStarred) R.drawable.baseline_star_24 else R.drawable.outline_star_24
        )

        holder.itemView.setOnClickListener { onTaskClick(task) }
        holder.buttonStar.setOnClickListener { onStarClick(task) }
    }

    override fun getItemCount() = tasks.size
}
