package com.example.task_manager.ui.list

import android.view.View
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.task_manager.R

class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val checkDone: CheckBox = view.findViewById(R.id.check_task_done)
    val textTitle: TextView = view.findViewById(R.id.text_task_title)
    val buttonStar: ImageButton = view.findViewById(R.id.button_star)
}
