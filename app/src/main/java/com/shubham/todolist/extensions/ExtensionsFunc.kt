package com.shubham.todolist.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.SpannableString
import android.text.style.StrikethroughSpan
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.shubham.todolist.R
import com.shubham.todolist.constant.FragmentType
import com.shubham.todolist.ui.FRAGMENT_TYPE
import com.shubham.todolist.ui.startFragmentHomeActivity

fun ViewGroup.getChildOrNull(index: Int): View? {
  return if (index < this.childCount) {
    this.getChildAt(index)
  } else {
    null
  }
}

fun Activity.hideKeyBoard() {
  val view = this.currentFocus
  if (view != null) {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
  }
}

fun Intent.setFragmentType(type: FragmentType): Intent {
  return this.putExtra(FRAGMENT_TYPE, type.ordinal)
}

fun AppCompatActivity.startHomeActivity() {
  try {
    startFragmentHomeActivity(FragmentType.FRAGMENT_TASK_LIST)
  } catch (e: ClassNotFoundException) {
    e.printStackTrace()
  }
}

fun TextView.strikeThrough(stringText:String){
  val strike = SpannableString(stringText)
  strike.setSpan(StrikethroughSpan(), 0, strike.length.minus(1), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
  this.text = strike
}

fun TextView.setTextColorCustom(color:Int){
  this.setTextColor(ContextCompat.getColor(this.context, color))
}