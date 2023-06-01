package com.shubham.todolist.db


import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

const val TASKS_DATABASE = "tasks_db"

@Database(entities = [Task::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private var instance: AppDatabase? = null
        fun getInstance(context: Application): AppDatabase? {
            if (instance == null) {
                synchronized(AppDatabase::class.java) {
                    if (instance == null) {
                        instance = Room
                            .databaseBuilder(context, AppDatabase::class.java, TASKS_DATABASE)
                            .fallbackToDestructiveMigration().build()
                    }
                }
            }
            return instance
        }
    }

    abstract fun taskDao(): TaskDao
}