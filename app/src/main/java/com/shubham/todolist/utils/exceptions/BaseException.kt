package com.shubham.todolist.utils.exceptions

open class BaseException : Exception {

  internal constructor() : super()
  internal constructor(message: String) : super(message)

  override fun toString(): String {
    return message ?: super.toString()
  }
}
