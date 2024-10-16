package com.example.lktasktracker.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.lktasktracker.data.TaskModel
import com.example.lktasktracker.databinding.ShortTaskViewHolderBinding

class ShortTaskAdapter(private val tasks: List<TaskModel>) :
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
        return asyncListDiffer.currentList.size
    }

    override fun onBindViewHolder(holder: ShortTaskViewHolder, position: Int) {
        val task = asyncListDiffer.currentList[position]
        holder.binding.taskViewShort.binding.apply {
            dateTv.text = task.expirationDate.run {
                "${this.date}/${this.month + 1}/${this.year}"
            }
            titleTv.text = task.title
        }
    }

    fun addItems(newTasks: List<TaskModel>) {
        val newItems = ArrayList(tasks)
        newItems.addAll(newTasks)
        asyncListDiffer.submitList(newItems)
    }

    private val diffUtil = object : DiffUtil.ItemCallback<TaskModel>() {
        override fun areItemsTheSame(oldItem: TaskModel, newItem: TaskModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TaskModel, newItem: TaskModel): Boolean {
            return oldItem == newItem
        }
    }

    private val asyncListDiffer = AsyncListDiffer(this, diffUtil)

    class ShortTaskViewHolder(val binding: ShortTaskViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root)
}