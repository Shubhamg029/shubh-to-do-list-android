package com.shubham.todolist.utils

import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.shubham.todolist.base.BaseActivity

object DialogUtils {

    fun materialConfirmationDialog(){
        MaterialAlertDialogBuilder(baseActivity)
            .setTitle(resources.getString(R.string.title))
            .setNeutralButton(resources.getString(R.string.cancel)) { dialog, which ->
                // Respond to neutral button press
            }
            .setPositiveButton(resources.getString(R.string.ok)) { dialog, which ->
                // Respond to positive button press
            }
            // Single-choice items (initialized with checked item)
            .setSingleChoiceItems(singleItems, checkedItem) { dialog, which ->
                // Respond to item chosen
            }
            .show()
    }
}