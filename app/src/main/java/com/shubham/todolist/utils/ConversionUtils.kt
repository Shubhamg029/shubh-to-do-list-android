package com.shubham.todolist.utils

import android.content.res.Resources

object ConversionUtils {

    /**
     * Value of dp to value of px.
     *
     * @param dpValue The value of dp.
     * @return value of px
     */
    fun dp2px(dpValue: Float): Int {
        val scale = Resources.getSystem().displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    fun dp2px(vararg dpValues: Float): Int {
        val scale = Resources.getSystem().displayMetrics.density
        var sum = 0
        for (dpValue in dpValues) {
            sum += (dpValue * scale + 0.5f).toInt()
        }
        return sum
    }
}