package com.shubham.todolist.utils

class IllegalFragmentTypeException : BaseException {

  constructor() : super("Invalid fragment type")

  constructor(message: String) : super(message)
}
