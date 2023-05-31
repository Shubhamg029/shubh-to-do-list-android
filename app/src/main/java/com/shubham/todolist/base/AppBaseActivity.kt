package com.shubham.todolist.base

import android.os.Bundle
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import com.shubham.todolist.R

abstract class AppBaseActivity<Binding : ViewDataBinding> : BaseActivity<Binding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView() {
    }

    override fun getToolbarBackgroundColor(): Int? {
        return ContextCompat.getColor(this, R.color.white)
    }

    override fun getToolbarTitleColor(): Int? {
        return ContextCompat.getColor(this, R.color.white)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
