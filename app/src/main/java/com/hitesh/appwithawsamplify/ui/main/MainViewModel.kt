package com.hitesh.appwithawsamplify.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.generated.model.Todo

class MainViewModel : ViewModel() {

    var todoList: MutableLiveData<ArrayList<Todo>> = MutableLiveData()
    val error: MutableLiveData<String>? = MutableLiveData()

    init {
        getDataFromBackend()
    }

    fun saveTodo(todoItem: Todo) {
        Amplify.DataStore.save(
            todoItem,
            { success -> Log.d("TODO insert", "Done ${success.item().name}") },
            { error -> Log.e("TOD insert", "failed", error) }
        )
    }

    fun getAllTodo() {
        val myTodoList = ArrayList<Todo>()
        Amplify.DataStore.query(
            Todo::class.java,
            {
                while (it.hasNext()) {
                    myTodoList.add(it.next())
                }
                todoList.postValue(myTodoList)
                error?.postValue(null)
            },
            { failure ->
                todoList.postValue(null)
                error?.postValue(failure.localizedMessage)
                Log.e("Tutorial", "Could not query DataStore", failure)
            }
        )

    }

    fun getDataFromBackend() {
        Amplify.DataStore.observe(
            Todo::class.java,
            { Log.d("@TODO call", "Task Started") },
            { Log.d("@TODO call", "Task Done ${it.item().name}") },
            { Log.d("@TODO call", "Task Error", it) },
            { Log.d("@TODO call", "Task Completed") }
        )
    }
}