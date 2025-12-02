package com.example.task_manager.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task_manager.databinding.FragmentTasksBinding
import com.example.task_manager.viewmodel.TaskViewModel

class CompletedTasksFragment(private val viewModel: TaskViewModel) : Fragment() {

    private lateinit var binding: FragmentTasksBinding
    private lateinit var adapter: TaskAdapter

    companion object {
        fun newInstance(viewModel: TaskViewModel) = CompletedTasksFragment(viewModel)
    }

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
            onTaskClick = {},
            onStarClick = {}
        )

        binding.recyclerViewTasks.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewTasks.adapter = adapter

        viewModel.tasks.observe(viewLifecycleOwner) { taskList ->
            val completedTasks = taskList.filter { it.isCompleted }
            adapter.updateData(completedTasks)
        }
    }
}
