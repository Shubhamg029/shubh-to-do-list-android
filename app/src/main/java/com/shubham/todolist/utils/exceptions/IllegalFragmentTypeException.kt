package com.shubham.todolist.utils.exceptions

import com.shubham.todolist.utils.exceptions.BaseException

class IllegalFragmentTypeException : BaseException {

  constructor() : super("Invalid fragment type")

  constructor(message: String) : super(message)
}
