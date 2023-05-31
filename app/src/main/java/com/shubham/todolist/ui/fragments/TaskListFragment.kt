package com.shubham.todolist.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.shubham.todolist.R
import com.shubham.todolist.base.AppBaseFragment
import com.shubham.todolist.constant.FragmentType
import com.shubham.todolist.constant.RecyclerViewActionType
import com.shubham.todolist.databinding.FragmentTaskListBinding
import com.shubham.todolist.recyclerView.AppBaseRecyclerViewAdapter
import com.shubham.todolist.recyclerView.BaseRecyclerViewItem
import com.shubham.todolist.recyclerView.RecyclerItemClickListener
import com.shubham.todolist.room.Task
import com.shubham.todolist.ui.startFragmentHomeActivity
import com.shubham.todolist.utils.dialog.DialogClickListener
import com.shubham.todolist.utils.dialog.DialogUtils.materialConfirmationDialog
import com.shubham.todolist.viewModel.TaskViewModel

class TaskListFragment : AppBaseFragment<FragmentTaskListBinding, TaskViewModel>(),
    RecyclerItemClickListener, DialogClickListener {

    private var taskList: ArrayList<Task> = arrayListOf()
    private var positionGlobal:Int = 0
    private var taskAdapter: AppBaseRecyclerViewAdapter<Task>? = null
    private lateinit var taskClicked:Task

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle? = null): TaskListFragment {
            val fragment = TaskListFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayout(): Int {
        return R.layout.fragment_task_list
    }

    override fun getViewModelClass(): Class<TaskViewModel> {
        return TaskViewModel::class.java
    }

    override fun onCreateView() {
        setOnClickListener(binding.fabAddTask)
        initMvvM()
    }

    @SuppressLint("FragmentLiveDataObserve")
    private fun initMvvM() {
        viewModel?.taskResult()?.observe(this) {
            taskList.clear()
            taskList.addAll(it)
            setUpUiForEmptyEdgeCase()
            populateAdapter()
        }
    }

    private fun setUpUiForEmptyEdgeCase() {
        if (taskList.isEmpty().not()) {
            binding.tvNoTasks.visibility = View.GONE
            binding.frameRvContent.visibility = View.VISIBLE
        } else {
            binding.tvNoTasks.visibility = View.VISIBLE
            binding.frameRvContent.visibility = View.GONE
        }
    }

    private fun populateAdapter() {
        binding.rvTaskList.layoutManager =
            LinearLayoutManager(baseActivity, LinearLayoutManager.VERTICAL, false)
        taskAdapter = AppBaseRecyclerViewAdapter(baseActivity, taskList, this)
        binding.rvTaskList.adapter = taskAdapter

    }

    override fun onClick(v: View) {
        super.onClick(v)
        when (v) {
            binding.fabAddTask ->
                baseActivity.startFragmentHomeActivity(
                    FragmentType.FRAGMENT_ADD_NEW_TASK,
                    clearTop = false
                )
        }
    }

    override fun onItemClick(position: Int, item: BaseRecyclerViewItem?, actionType: Int) {
        taskClicked = item as Task
        when (actionType) {
            RecyclerViewActionType.TASK_COMPLETE_CHECKBOX_CLICK.ordinal -> {
                when (taskClicked.isCompleted) {
                    true -> taskClicked.isCompleted = false
                    false -> taskClicked.isCompleted = true
                    else -> taskClicked.isCompleted = false
                }
                taskList[position].isCompleted = taskClicked.isCompleted
                viewModel?.updateTask(taskClicked.isCompleted == true, taskClicked)
            }

            RecyclerViewActionType.TASK_REMOVE_CLICK.ordinal -> {
                positionGlobal = position
                val message = baseActivity.getString(R.string.do_you_want_to_delete_message).replace("---", "\"${taskClicked.taskTitle.toString()}\"")
                materialConfirmationDialog(baseActivity, this, message = message, title = getString(R.string.warning))
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel?.loadTasks()
    }

    override fun onPositiveButtonClick() {
        var pos = positionGlobal
        if (taskList.size == 1) {
            pos = 0
        }
        taskList.removeAt(pos)
        taskAdapter?.notifyItemRemoved(pos)
        viewModel?.deleteTask(taskClicked)
        if (taskAdapter?.itemCount == 0) {
            setUpUiForEmptyEdgeCase()
        }
    }

    override fun onNegativeButtonClick() {
        //To handle Negative click
    }
}