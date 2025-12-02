package com.example.task_manager.ui.addedit

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.example.task_manager.databinding.DialogAddTaskBinding
import com.example.task_manager.data.Task

class AddEditTaskDialog(
    private val existingTask: Task? = null,
    private val onSave: (Task) -> Unit
) : BottomSheetDialogFragment() {

    private lateinit var binding: DialogAddTaskBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext())
        binding = DialogAddTaskBinding.inflate(LayoutInflater.from(context))
        dialog.setContentView(binding.root)

        if (existingTask != null) {
            binding.editTextTaskTitle.setText(existingTask.taskTitle)
            binding.editTextTaskDescription.setText(existingTask.taskDescription)
        }

        binding.buttonSaveTask.setOnClickListener {
            val title = binding.editTextTaskTitle.text.toString()
            val desc = binding.editTextTaskDescription.text.toString()

            if (title.isNotBlank()) {
                val task = existingTask?.copy(
                    taskTitle = title,
                    taskDescription = desc
                ) ?: Task(taskTitle = title, taskDescription = desc)

                onSave(task)
                dismiss()
            }
        }

        return dialog
    }
}
