package com.shubham.todolist.utils.dialog

import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.shubham.todolist.R
import com.shubham.todolist.base.BaseActivity

object DialogUtils {

    fun materialConfirmationDialog(baseActivity: BaseActivity<*>,
                                   dialogClickListener: DialogClickListener,
                                   title: String,
                                   message:String){
        MaterialAlertDialogBuilder(baseActivity)
            .setTitle(title)
            .setMessage(message)
            .setNeutralButton(baseActivity.getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton(baseActivity.getString(R.string.ok)) { dialog, _ ->
                dialogClickListener.onPositiveButtonClick()
                dialog.dismiss()
            }
            .show()
    }
}