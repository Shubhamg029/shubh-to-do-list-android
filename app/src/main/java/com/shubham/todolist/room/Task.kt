package com.shubham.todolist.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shubham.todolist.constant.RecyclerViewItemType
import com.shubham.todolist.recyclerView.AppBaseRecyclerViewItem
import java.io.Serializable

@Entity(tableName = "TaskList")
data class Task(
    @PrimaryKey val timeStamp: Long,
    @ColumnInfo(name = "task_title") val taskTitle: String?,
    @ColumnInfo(name = "is_completed") var isCompleted: Boolean?,
    @ColumnInfo(name = "am_pm") val amPM: String?,
    @ColumnInfo(name = "task_time") val taskTime: String?,
    @ColumnInfo(name = "task_time_milliseconds") val taskTimeInMilliseconds: Long?,
    @ColumnInfo(name = "is_pending") val isPending: Boolean?,
): Serializable, AppBaseRecyclerViewItem {

    override fun getViewType(): Int {
        return RecyclerViewItemType.TASK_LIST_ITEM.getLayout()
    }
}
