package com.example.task_manager.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.task_manager.data.TaskDatabase
import com.example.task_manager.data.TaskRepository
import com.example.task_manager.databinding.ActivityMainBinding
import com.example.task_manager.databinding.DialogAddTaskBinding
import com.example.task_manager.viewmodel.TaskViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Database + Repository + ViewModel
        val db = TaskDatabase.createDatabase(this)
        val repo = TaskRepository(db.getTaskDAO())
        taskViewModel = ViewModelProvider(
            this,
            TaskViewModel.Factory(repo)
        )[TaskViewModel::class.java]

        setupViewPager()
        setupFab()
    }

    private fun setupViewPager() {
        binding.pagerHome.adapter = HomePagerAdapter(this, taskViewModel)

        TabLayoutMediator(binding.tabsHome, binding.pagerHome) { tab, pos ->
            tab.text = when(pos) {
                0 -> "My Tasks"
                1 -> "Starred"
                else -> "Completed"
            }
        }.attach()
    }

    private fun setupFab() {
        binding.addTaskButton.setOnClickListener {
            val dialog = BottomSheetDialog(this)
            val addBinding = DialogAddTaskBinding.inflate(layoutInflater)
            dialog.setContentView(addBinding.root)

            addBinding.buttonSaveTask.setOnClickListener {
                val title = addBinding.editTextTaskTitle.text.toString()
                val desc = addBinding.editTextTaskDescription.text.toString()

                if(title.isNotBlank()) {
                    taskViewModel.addTask(title, desc)
                    dialog.dismiss()
                }
            }

            dialog.show()
        }
    }
}
