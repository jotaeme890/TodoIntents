package com.alanturing.cpifp.todo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alanturing.cpifp.todo.databinding.TodoItemBinding
import com.alanturing.cpifp.todo.model.Task

class TasksAdapter(private val datos:List<Task>,
                   val onShare:((task: Task)-> Unit),
                   val onEdit:((task: Task) -> Unit),
                   val onCompleted:((task: Task, b: Boolean) -> Unit)): RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(val binding: TodoItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindTask(t:Task){
            binding.todoTitle.text = t.title
            binding.todoDescription.text = t.description
            binding.todoSw.isChecked = t.isCompleted
            binding.todoSw.setOnCheckedChangeListener{_, completed ->
                onCompleted(t, completed)
            }
            binding.share.setOnClickListener(){
                onShare(t)
            }
            binding.edit.setOnClickListener(){
                onEdit(t)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = TodoItemBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false,)
        return TaskViewHolder(binding)
    }

    override fun getItemCount() = datos.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = datos[position]
        holder.bindTask(task)
    }
}