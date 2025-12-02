package com.example.task_manager.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.task_manager.ui.list.AllTasksFragment
import com.example.task_manager.ui.list.CompletedTasksFragment
import com.example.task_manager.ui.list.StarredTasksFragment
import com.example.task_manager.viewmodel.TaskViewModel

class HomePagerAdapter(
    activity: FragmentActivity,
    private val viewModel: TaskViewModel
) : FragmentStateAdapter(activity) {

    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> AllTasksFragment.newInstance(viewModel)
            1 -> StarredTasksFragment.newInstance(viewModel)
            2 -> CompletedTasksFragment.newInstance(viewModel)
            else -> AllTasksFragment.newInstance(viewModel)
        }
    }
}
