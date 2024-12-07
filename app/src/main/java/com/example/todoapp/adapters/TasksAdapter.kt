
package com.example.todoapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.Model.Task
import com.example.todoapp.databinding.TaskRecyclerviewBinding
import java.text.SimpleDateFormat
import java.util.Locale

class TasksAdapter(var tasks: List<Task>) : RecyclerView.Adapter<TasksAdapter.Taskviewholder>() {

    class Taskviewholder(val binding: TaskRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.tv.text = task.title
            val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            val formattedTime = dateFormat.format(task.date)
            binding.tvTime.text = formattedTime
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Taskviewholder {
        val binding = TaskRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Taskviewholder(binding)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: Taskviewholder, position: Int) {
        val task = tasks[position]
        holder.bind(task)
    }
    fun updateData(newTasks: List<Task>) {
        tasks = newTasks
        notifyDataSetChanged()
    }
}
