package com.example.task_manager.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task_manager.databinding.FragmentTasksBinding
import com.example.task_manager.ui.list.TaskAdapter
import com.example.task_manager.ui.addedit.AddEditTaskDialog
import com.example.task_manager.viewmodel.TaskViewModel

class TasksFragment(private val viewModel: TaskViewModel) : Fragment() {

    private lateinit var binding: FragmentTasksBinding
    private lateinit var adapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTasksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = TaskAdapter(
            emptyList(),
            onTaskClick = { task ->
                AddEditTaskDialog(task) {
                    viewModel.updateTask(it)
                }.show(childFragmentManager, "edit")
            },
            onStarClick = { task ->
                viewModel.updateTask(task.copy(isStarred = !task.isStarred))
            }
        )

        binding.recyclerViewTasks.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewTasks.adapter = adapter

        // Observe tasks LiveData
        viewModel.tasks.observe(viewLifecycleOwner) { taskList ->
            adapter.updateData(taskList)
        }

        // ❌ REMOVE: viewModel.loadTasks()
    }
}
