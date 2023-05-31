package com.shubham.todolist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shubham.todolist.R
import com.shubham.todolist.base.AppBaseActivity
import com.shubham.todolist.databinding.ActivityHomeBinding

class HomeActivity : AppBaseActivity<ActivityHomeBinding>() {

    override fun getLayout(): Int {
        return R.layout.activity_home
    }

}