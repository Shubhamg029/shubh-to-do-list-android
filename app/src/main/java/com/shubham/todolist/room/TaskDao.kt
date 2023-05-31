package com.shubham.todolist.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Single

@Dao
interface TaskDao {

    @Query("SELECT * FROM TaskList")
    fun getAll() : Single<List<Task>>

    @Insert
    fun insertTask(task: Task)

    @Insert
    fun insertAll(vararg tasks: Task)

    @Query("DELETE FROM TaskList WHERE timeStamp=:timeStamp")
    fun deleteSpecificTask(timeStamp: Long)

    @Query("UPDATE TaskList SET is_completed = :isCompleted WHERE timeStamp = :timeStamp")
    fun updateTask(isCompleted:Boolean, timeStamp: Long)
}