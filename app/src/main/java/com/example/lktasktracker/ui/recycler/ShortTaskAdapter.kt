package com.example.lktasktracker.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lktasktracker.data.TaskModel
import com.example.lktasktracker.databinding.ShortTaskViewHolderBinding

class ShortTaskAdapter(val tasks: MutableList<TaskModel>) :
    RecyclerView.Adapter<ShortTaskAdapter.ShortTaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShortTaskViewHolder {
        return ShortTaskViewHolder(
            ShortTaskViewHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: ShortTaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.binding.taskViewShort.binding.apply {
            dateTv.text = task.expirationDate.run {
                "${this.date}/${this.month + 1}/${this.year}"
            }
            titleTv.text = task.title
        }
    }

    fun updateItems(newTasks: List<TaskModel>) {
        tasks.removeAll(tasks)
        tasks.addAll(newTasks)
        notifyDataSetChanged()
    }

    class ShortTaskViewHolder(val binding: ShortTaskViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root)
}