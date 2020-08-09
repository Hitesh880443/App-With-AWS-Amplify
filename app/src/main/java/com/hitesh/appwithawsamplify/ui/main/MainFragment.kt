package com.hitesh.appwithawsamplify.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.amplifyframework.datastore.generated.model.Priority
import com.amplifyframework.datastore.generated.model.Todo
import com.hitesh.appwithawsamplify.BR
import com.hitesh.appwithawsamplify.R
import com.hitesh.appwithawsamplify.databinding.MainFragmentBinding
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    lateinit var mViewDataBinding: MainFragmentBinding
    private lateinit var viewModel: MainViewModel
    private var adapter = TodoAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewDataBinding =
            DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        return mViewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mViewDataBinding.setVariable(BR.viewmodel, viewModel)
        mViewDataBinding.executePendingBindings()
        setUI()
    }

    private fun setUI() {
        rv_todolist.layoutManager = LinearLayoutManager(activity)
        rv_todolist.adapter = adapter

        btn_add_todo.setOnClickListener {
            val todoItem: Todo = Todo.builder()
                .name(et_todoname.text.toString())
                .description(et_description.text.toString())
                .priority(Priority.HIGH)
                .build()
            viewModel.saveTodo(todoItem)
        }

        btn_get_todos.setOnClickListener {
            viewModel.getAllTodo()
        }

        viewModel.todoList.observe(viewLifecycleOwner, Observer {
            adapter.setList(it)
        })
    }
}