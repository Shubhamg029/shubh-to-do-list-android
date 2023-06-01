package com.shubham.todolist.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shubham.todolist.base.BaseViewModel
import com.shubham.todolist.db.AppDatabase
import com.shubham.todolist.db.Task
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TaskViewModel(application: Application) : BaseViewModel(application){

    var taskResult: MutableLiveData<List<Task>> = MutableLiveData()
    var taskLoader: MutableLiveData<Boolean> = MutableLiveData()
    var taskError: MutableLiveData<String> = MutableLiveData()

    fun taskResult(): LiveData<List<Task>> {
        return taskResult
    }

    fun loadTasks() {
        CompositeDisposable().add(
            AppDatabase.getInstance(getApplication())!!
                .taskDao()
                .getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        taskResult.postValue(it)
                        taskLoader.postValue(false)
                    },
                    {
                        it.printStackTrace()
                        taskError.postValue(it.message)
                        taskLoader.postValue(false)
                    }
                )
        )
    }

    fun addItemToTasks(task: Task) {
        taskLoader.postValue(true)
        Completable.fromAction {
            AppDatabase
                .getInstance(getApplication())!!
                .taskDao()
                .insertTask(task)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete {
                taskLoader.postValue(false)
            }
            .doOnError {
                taskError.postValue(it.message)
                taskLoader.postValue(false)
            }
            .subscribe()
    }

    fun addAllTasks(task: Task) {
        taskLoader.postValue(true)
        Completable.fromAction {
            AppDatabase
                .getInstance(getApplication())!!
                .taskDao()
                .insertAll(task)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete {
                taskLoader.postValue(false)
            }
            .doOnError {
                taskError.postValue(it.message)
                taskLoader.postValue(false)
            }
            .subscribe()
    }

    fun deleteTask(task: Task) {
        taskLoader.postValue(true)
        Completable.fromAction {
            AppDatabase
                .getInstance(getApplication())!!
                .taskDao()
                .deleteSpecificTask(task.timeStamp)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete {
                taskLoader.postValue(false)
            }
            .doOnError {
                taskError.postValue(it.message)
                taskLoader.postValue(false)
            }
            .subscribe()
    }

    fun updateTask(isCompleted:Boolean, task: Task) {
        taskLoader.postValue(true)
        Completable.fromAction {
            AppDatabase
                .getInstance(getApplication())!!
                .taskDao()
                .updateTask(isCompleted, task.timeStamp)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete {
                taskLoader.postValue(false)
            }
            .doOnError {
                taskError.postValue(it.message)
                taskLoader.postValue(false)
            }
            .subscribe()
    }
}