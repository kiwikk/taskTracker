package com.example.dialog

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.view.WindowManager
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewmodel.MutableCreationExtras
import com.example.lktasktracker.R
import com.example.lktasktracker.data.TaskModel
import com.example.lktasktracker.databinding.AddTaskDialogBinding
import com.example.lktasktracker.ui.fragments.todo.ToDoViewModel
import java.util.Calendar
import java.util.Date

class AddTaskDialog : DialogFragment(R.layout.add_task_dialog) {
    private var binding: AddTaskDialogBinding? = null
    private val viewModel: ToDoViewModel by activityViewModels(
        factoryProducer = { ToDoViewModel.Factory },
        extrasProducer = {
            MutableCreationExtras().apply {
                set(ToDoViewModel.APPLICATION_KEY, requireActivity().application)
            }
        })

    var year: Int
    var month: Int
    var day: Int

    init {
        val c = Calendar.getInstance()
        year = c.get(Calendar.YEAR)
        month = c.get(Calendar.MONTH)
        day = c.get(Calendar.DAY_OF_MONTH)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = AddTaskDialogBinding.inflate(layoutInflater)
        requireActivity().getWindow()
            .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)

        binding!!.taskDate.setOnClickListener {
            openDatePicker()
        }

        return AlertDialog.Builder(requireContext())
            .setView(binding!!.root)
            .setMessage("Creating a task")
            .setPositiveButton("Ok", { _, _ -> viewModel.addTask(createTask()) })
            .setNegativeButton("Cancel", { dialog, _ -> dialog.dismiss() })
            .create()
    }

    override fun onDestroyView() {
        requireActivity().getWindow()
            .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

        super.onDestroyView()
    }

    private fun openDatePicker() {
        DatePickerDialog(requireContext(), object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
                binding?.taskDateTv?.text = "Date selected: $p3.${p2+1}.$p1"
                year = p1
                month = p2
                day = p3
            }
        }, year, month, day).show()
    }

    private fun createTask(): TaskModel {
        val title = binding?.taskTitle?.text.toString()
        val description = binding?.taskDescription?.text.toString()
        val date = Date(year, month, day)

        return TaskModel(title = title, description = description, expirationDate = date)
    }
}