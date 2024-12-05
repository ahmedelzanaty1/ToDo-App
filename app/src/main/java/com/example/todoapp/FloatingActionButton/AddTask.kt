package com.example.todoapp.FloatingActionButton

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import com.example.todoapp.DataBase.TaskDataBase
import com.example.todoapp.Model.Task
import com.example.todoapp.databinding.AddTaskBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AddTask : BottomSheetDialogFragment() {
    lateinit var binding : AddTaskBinding
    lateinit var calendar: Calendar
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ButtonSave()
        binding.pickDate.setOnClickListener {
            Datepicker()
        }
    }
    fun ButtonSave(){
        binding.addTaskBtn.setOnClickListener {
            val task = Task(title = binding.titleText.text.toString(),
                description = binding.descriptionText.text.toString(),
                date = calendar.time,
                isCompleted = false)
            if(validatefields()){
                TaskDataBase.getDatabase(requireContext()).getdao().inserttask(task)
                dismiss()
            }

        }
    }
    fun validatefields():Boolean{
        if(binding.titleText.text.toString().isEmpty()|| binding.titleText.text.toString().isBlank()){

            binding.titleText.error = "enter title"
            return false
        }
        if(binding.descriptionText.text.toString().isEmpty()|| binding.descriptionText.text.toString().isBlank()){
            binding.descriptionText.error = "enter description"
            return false
        }
        return true
    }
    fun Datepicker(){
         calendar = Calendar.getInstance()

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val timePickerDialog = TimePickerDialog(
                    requireContext(),
                    { _, hourOfDay, minute ->
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                        calendar.set(Calendar.MINUTE, minute)
                        val dateTime = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault()).format(calendar.time)
                        binding.dateValue.text = dateTime
                    },
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    false
                )
                timePickerDialog.show()
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()

    }
}