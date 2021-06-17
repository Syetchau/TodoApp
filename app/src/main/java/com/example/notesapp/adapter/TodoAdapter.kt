package com.example.notesapp.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.databinding.TodoLayoutAdapterBinding
import com.example.notesapp.model.Todo
import com.example.notesapp.utils.OnClickListener
import com.google.android.material.snackbar.Snackbar

class TodoAdapter: RecyclerView.Adapter<TodoAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: TodoLayoutAdapterBinding): RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object: DiffUtil.ItemCallback<Todo>(){
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)
    private lateinit var listener: OnClickListener
    var mTodo: MutableList<Todo>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    fun setListener(listener: OnClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(TodoLayoutAdapterBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val todo = mTodo[position]

        holder.binding.apply {
            tvTodoItemTitle.text = todo.todoTitle
        }

        holder.binding.cbTodoItem.apply {
            setOnClickListener {
                holder.binding.apply {
                    if (isChecked) {
                        tvTodoItemTitle.paintFlags = tvTodoItemTitle.paintFlags or
                                Paint.STRIKE_THRU_TEXT_FLAG
                    } else {
                        tvTodoItemTitle.paintFlags = tvTodoItemTitle.paintFlags and
                                Paint.STRIKE_THRU_TEXT_FLAG.inv()
                    }
                }
            }
        }
    }

    override fun getItemCount() = mTodo.size
}