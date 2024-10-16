package com.example.lktasktracker.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lktasktracker.data.TaskModel
import com.example.lktasktracker.databinding.FullTaskViewHolderBinding

class FullTaskAdapter(private val tasks: MutableList<TaskModel>) :
    RecyclerView.Adapter<FullTaskAdapter.FullTaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FullTaskViewHolder {
        return FullTaskViewHolder(
            FullTaskViewHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: FullTaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.binding.taskViewFull.binding.apply {
            dateTv.text = task.expirationDate.run {
                "${this.date}/${this.month + 1}/${this.year}"
            }
            descriptionTv.text = task.description
            titleTv.text = task.title
        }
    }

    override fun getItemId(position: Int): Long {
        return tasks[position].id.node()
    }

    fun updateItems(newTasks: List<TaskModel>) {
        tasks.removeAll(tasks)
        tasks.addAll(newTasks)
        notifyDataSetChanged()
    }

    class FullTaskViewHolder(val binding: FullTaskViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root)
}