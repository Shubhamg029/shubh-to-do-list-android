package com.shubham.todolist.ui

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.shubham.todolist.R
import com.shubham.todolist.base.AppBaseActivity
import com.shubham.todolist.base.BaseFragment
import com.shubham.todolist.constant.FragmentType
import com.shubham.todolist.databinding.ActivityHomeContainerBinding
import com.shubham.todolist.utils.extensions.setFragmentType
import com.shubham.todolist.ui.fragments.AddNewTaskFragment
import com.shubham.todolist.ui.fragments.TaskListFragment
import com.shubham.todolist.utils.ConversionUtils
import com.shubham.todolist.utils.customViews.CustomToolbar
import com.shubham.todolist.utils.exceptions.IllegalFragmentTypeException

const val FRAGMENT_TYPE = "FRAGMENT_TYPE"

open class HomeContainerActivity : AppBaseActivity<ActivityHomeContainerBinding>() {

    private var type: FragmentType? = null

    override fun getLayout(): Int {
        return R.layout.activity_home_container
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        intent?.extras?.getInt(FRAGMENT_TYPE)?.let { type = FragmentType.values()[it] }
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView() {
        super.onCreateView()
        setFragment()
    }

    override fun getToolbar(): CustomToolbar? {
        return binding?.appBarLayout?.toolbar
    }

    override fun getToolbarBackgroundColor(): Int? {
        return when (type) {
            FragmentType.FRAGMENT_TASK_LIST -> ContextCompat.getColor(this, R.color.pink_FEF7FF)
            else -> super.getToolbarBackgroundColor()
        }
    }

    override fun getToolbarTitleSize(): Float? {
        return when (type) {
            FragmentType.FRAGMENT_ADD_NEW_TASK -> ConversionUtils.dp2px(22f).toFloat()
            else -> super.getToolbarTitleSize()
        }
    }

    override fun getToolbarTitleColor(): Int? {
        return when (type) {
            FragmentType.FRAGMENT_ADD_NEW_TASK -> ContextCompat.getColor(this, R.color.black_1D1B20)
            else -> super.getToolbarTitleColor()
        }
    }

    override fun getNavigationIcon(): Drawable? {
        return ContextCompat.getDrawable(this, R.drawable.ic_arrow_back)
    }

    override fun getToolbarTitle(): String? {
        return when (type) {
            FragmentType.FRAGMENT_ADD_NEW_TASK -> resources.getString(R.string.add_a_task)
            else -> super.getToolbarTitle()
        }
    }

    private fun setFragment() {
        val fragment = getFragmentInstance(type)
        fragment?.arguments = intent.extras
        binding?.container?.id?.let { addFragmentReplace(it, fragment, shouldAddToBackStack()) }
    }

    private fun shouldAddToBackStack(): Boolean {
        return when (type) {
            else -> false
        }
    }

    private fun getFragmentInstance(type: FragmentType?): BaseFragment<*, *>? {
        return when (type) {
            FragmentType.FRAGMENT_TASK_LIST -> TaskListFragment.newInstance()
            FragmentType.FRAGMENT_ADD_NEW_TASK -> AddNewTaskFragment.newInstance()
            else -> throw IllegalFragmentTypeException()
        }
    }
}

fun AppCompatActivity.startFragmentHomeActivity(type: FragmentType, bundle: Bundle = Bundle(), clearTop: Boolean = false) {
    val intent = Intent(this, HomeContainerActivity::class.java)
    intent.putExtras(bundle)
    intent.setFragmentType(type)
    if (clearTop) intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    startActivity(intent)
}