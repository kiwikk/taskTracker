package com.example.tasktracker.ui.fragments.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.lktasktracker.R
import com.example.lktasktracker.databinding.TaskViewShortBinding

class TaskViewShort @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    val binding: TaskViewShortBinding

    init {
        binding = TaskViewShortBinding.inflate(LayoutInflater.from(context), this, true)
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TaskViewShort)
        val taskName = typedArray.getString(R.styleable.TaskViewShort_titleText)
        val dateText = typedArray.getString(R.styleable.TaskViewShort_dateText)

        typedArray.recycle()

        binding.titleTv.text = taskName
        binding.dateTv.text = dateText
    }
}