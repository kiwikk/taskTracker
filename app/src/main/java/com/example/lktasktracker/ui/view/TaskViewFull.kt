package com.example.tasktracker.ui.fragments.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.lktasktracker.R
import com.example.lktasktracker.databinding.TaskViewFullBinding

class TaskViewFull @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): LinearLayout(context, attrs, defStyleAttr) {
    val binding: TaskViewFullBinding

    init {
        binding = TaskViewFullBinding.inflate(LayoutInflater.from(context), this, true)
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TaskViewFull)
        val taskName = typedArray.getString(R.styleable.TaskViewFull_titleFullText)
        val taskDescription = typedArray.getString(R.styleable.TaskViewFull_descrText)
        val dateText = typedArray.getString(R.styleable.TaskViewFull_dateFullText)
        typedArray.recycle()

        binding.titleTv.text = taskName
        binding.descriptionTv.text = taskDescription
        binding.dateTv.text = dateText
    }
}