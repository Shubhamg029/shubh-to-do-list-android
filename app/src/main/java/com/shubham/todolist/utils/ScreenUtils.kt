package com.shubham.todolist.utils

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.os.Build
import android.util.DisplayMetrics
import android.view.Window
import androidx.annotation.NonNull


class ScreenUtils {

  companion object {
    val instance = ScreenUtils()
  }

  fun getDisplayMetrics(activity: Activity?): DisplayMetrics {
    val displayMetrics = DisplayMetrics()
    activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
    return displayMetrics
  }

  fun getWidth(activity: Activity?): Int {
    return getDisplayMetrics(activity).widthPixels
  }

}