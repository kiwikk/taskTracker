package com.example.lktasktracker.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.lktasktracker.data.TaskModel
import com.example.lktasktracker.databinding.FullTaskViewHolderBinding
import com.example.lktasktracker.ui.fragments.todo.ToDoViewModel

class FullTaskAdapter(private val tasks: List<TaskModel>, private val vm: ToDoViewModel) :
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
        return asyncListDiffer.currentList.size
    }

    override fun onBindViewHolder(holder: FullTaskViewHolder, position: Int) {
        val task = asyncListDiffer.currentList[position]
        holder.binding.taskViewFull.binding.apply {
            dateTv.text = task.expirationDate.run {
                "${this.date}/${this.month + 1}/${this.year}"
            }
            descriptionTv.text = task.description
            titleTv.text = task.title

            buttonDone.setOnClickListener {
                vm.markTaskAsDone(task)
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return asyncListDiffer.currentList[position].id.node()
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

    class FullTaskViewHolder(val binding: FullTaskViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root)
}