package com.shubham.todolist.formData

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.shubham.todolist.ui.HomeContainerActivity
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddNewTaskTest {

    @get:Rule
    var mActivityRule = ActivityScenarioRule(HomeContainerActivity::class.java)

    @Before
    fun setUp(){
    }
}