package com.shubham.todolist.ui.fragments

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.shubham.todolist.R
import com.shubham.todolist.base.BaseFragment
import com.shubham.todolist.databinding.FragmentAddNewTaskBinding
import com.shubham.todolist.db.Task
import com.shubham.todolist.utils.TimeUtils
import com.shubham.todolist.viewModel.TaskViewModel
import java.util.Calendar

class AddNewTaskFragment : BaseFragment<FragmentAddNewTaskBinding, TaskViewModel>() {

    private var taskTime:String = ""
    private var taskTimeInMilliseconds:Long = 0L
    private var isPM:Boolean = false

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle? = null): AddNewTaskFragment {
            val fragment = AddNewTaskFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayout(): Int {
        return R.layout.fragment_add_new_task
    }

    override fun getViewModelClass(): Class<TaskViewModel> {
        return TaskViewModel::class.java
    }

    override fun onCreateView() {
        setOnClickListener(binding.btnAddTask, binding.btnCancel, binding.tipTime, binding.edtTime)
        setUpDropDownDayTime()
    }



    private fun setUpDropDownDayTime() {
        binding.autoTextAmPm.apply {
            onItemSelectedListener = object : OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    isPM = true
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    isPM = true
                }

            }
        }
    }

    override fun onClick(v: View) {
        when (v) {
            binding.btnAddTask -> addTaskToDb()
            binding.btnCancel -> cancelAndClosePage()
            binding.tipTime, binding.edtTime -> openTimePicker()
        }
    }

    /**
     * Shows the time picker dialog.
     */
    private fun openTimePicker() {
        val picker =
            MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setTitleText("Select Task Time")
                .build()
        picker.show(this.parentFragmentManager, "task_time_picker")

        picker.addOnPositiveButtonClickListener {
            timeCalculation(picker)
            //binding.autoTextAmPm.setText(if (isPM) "PM" else "AM")
        }
    }

    private fun timeCalculation(picker: MaterialTimePicker) {
        var hour = picker.hour
        if (hour > 12){
            hour = hour.minus(12)
            isPM = true
        }else if(hour == 12){
            isPM = true
        } else if (hour == 0){
            hour = hour.plus(12)
        }
        val timeWithoutDay  = TimeUtils.formatDigitPlaces(hour, picker.minute)
        binding.edtTime.setText(timeWithoutDay)
        taskTime = timeWithoutDay

        val cal = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY, picker.hour)
        cal.set(Calendar.MINUTE, picker.minute)
        taskTimeInMilliseconds = cal.timeInMillis
        Log.i("yellow", "AddNewTask Time in milli:".plus(taskTimeInMilliseconds))
    }

    private fun cancelAndClosePage() {
        baseActivity.setResult(RESULT_CANCELED)
        baseActivity.finish()
    }

    private fun addTaskToDb() {
        if(validateData()){
            val taskTitle = binding.edtTaskTitle.text.toString()
            val amPmTime = binding.autoTextAmPm.text.toString()
            viewModel?.addItemToTasks(
                Task(
                    timeStamp = System.currentTimeMillis(),
                    taskTitle = taskTitle,
                    isCompleted = false,
                    amPM = amPmTime,
                    taskTime = taskTime,
                    taskTimeInMilliseconds = taskTimeInMilliseconds,
                    isPending = false)
            )
            viewModel?.loadTasks()
            baseActivity.setResult(RESULT_OK)
            baseActivity.finish()
        }
    }

    private fun validateData(): Boolean {
        if (binding.edtTaskTitle.text?.trim().isNullOrEmpty()){
            showShortToast(getString(R.string.please_enter_title))
            return false
        }
        /*if (isTimeValidFormat(binding.edtTime.text?.trim().toString())){
            return false
        }*/
        return true
    }

    private fun isTimeValidFormat(timeString: String): Boolean {
        return true
    }
}