package com.hitesh.appwithawsamplify.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.amplifyframework.datastore.generated.model.Todo
import com.hitesh.appwithawsamplify.BR
import com.hitesh.appwithawsamplify.R
import com.hitesh.appwithawsamplify.databinding.RowTodoBinding

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.ViewHolder>() {
    private var todoList: List<Todo> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: RowTodoBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.row_todo, parent, false
        )
        return ViewHolder(binding)
    }

    fun setList(todoList: List<Todo>) {
        this.todoList = todoList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(todoList[position])
    }

    class ViewHolder(private val rowTodoBinding: RowTodoBinding) :
        RecyclerView.ViewHolder(rowTodoBinding.root) {
        fun bind(todoItem: Todo) {
            rowTodoBinding.setVariable(BR.todoItem, todoItem)
            rowTodoBinding.executePendingBindings()
        }
    }
}