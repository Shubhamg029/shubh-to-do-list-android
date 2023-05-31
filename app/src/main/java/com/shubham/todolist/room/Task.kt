package com.shubham.todolist.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskDataClass(
    @PrimaryKey val timeStamp: Int,
    @ColumnInfo(name = "task_title") val taskTitle: String?,
    @ColumnInfo(name = "is_completed") val isCompleted: Boolean?,
    @ColumnInfo(name = "is_pending") val isPending: Boolean?
)
