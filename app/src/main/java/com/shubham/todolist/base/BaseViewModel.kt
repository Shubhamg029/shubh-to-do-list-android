package com.shubham.todolist.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable

open class BaseViewModel(application: Application) : AndroidViewModel(application)

/*
fun Observable<BaseResponse>.toLiveData(strategy: BackpressureStrategy = BackpressureStrategy.BUFFER): LiveData<BaseResponse> {
  return LiveDataReactiveStreams.fromPublisher(this.toFlowable(strategy))
}*/
