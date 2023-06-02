package com.shubham.todolist.recyclerView.viewHolder

import android.view.View
import com.shubham.todolist.R
import com.shubham.todolist.constant.RecyclerViewActionType
import com.shubham.todolist.databinding.ItemTaskBinding
import com.shubham.todolist.db.Task
import com.shubham.todolist.utils.extensions.setTextColorCustom
import com.shubham.todolist.utils.extensions.strikeThrough
import com.shubham.todolist.recyclerView.AppBaseRecyclerViewHolder
import com.shubham.todolist.recyclerView.BaseRecyclerViewItem
import com.shubham.todolist.utils.TimeUtils


class TasksViewHolder(binding: ItemTaskBinding) :
    AppBaseRecyclerViewHolder<ItemTaskBinding>(binding) {

    override fun bind(position: Int, item: BaseRecyclerViewItem) {
        super.bind(position, item)
        val task = item as Task

        binding.chkBxTask.isChecked = task.isCompleted == true
        val time = task.taskTime.plus(" ").plus(task.amPM)
        binding.tvTime.text = time

        compareIfTaskPending(task)

        binding.ivRemove.setOnClickListener {
            listener?.onItemClick(position, item, RecyclerViewActionType.TASK_REMOVE_CLICK.ordinal)
        }

        binding.chkBxTask.setOnClickListener {
            compareIfTaskPending(task)
            listener?.onItemClick(position, item, RecyclerViewActionType.TASK_COMPLETE_CHECKBOX_CLICK.ordinal)
        }
    }

    private fun compareIfTaskPending(task: Task) {
        if (binding.chkBxTask.isChecked) {
            binding.tvPending.visibility = View.GONE
            binding.tvTaskTitle.strikeThrough(task.taskTitle.toString())
            binding.tvTaskTitle.setTextColorCustom(R.color.black_161717)
        }else if (TimeUtils.compareTimeStampIfPast(task.taskTimeInMilliseconds ?: 0L)){
            binding.tvPending.visibility = View.VISIBLE
            binding.tvTaskTitle.text = task.taskTitle
            binding.tvTaskTitle.setTextColorCustom(R.color.red_EF0000)
        } else {
            binding.tvPending.visibility = View.GONE
            binding.tvTaskTitle.text = task.taskTitle
            binding.tvTaskTitle.setTextColorCustom(R.color.black_161717)
        }
    }
}
