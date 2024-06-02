package com.mysolution.jetpack;

/**
 * Author: github.com/micro3721
 * E-mail: mysolution@qq.com
 * I provide software outsourcing services
 * 我提供软件定制开发服务,欢迎垂询
 * Created on 2024/5/25
 * Describe:
 */
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var taskViewModel: TaskViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TaskListAdapter
    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerView = findViewById(R.id.recyclerView)
        adapter = TaskListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]
        taskViewModel.allTasks.observe(this, Observer { tasks ->
            tasks?.let {
                adapter.submitList(it)
                Log.d("MainActivity", "Tasks updated: ${it.size}")
                it.forEach { task ->
                    Log.d("MainActivity", "Task: ${task.id} - ${task.name}")
                }
            }
        })
        taskViewModel.insertInitialTasks()


        // Use RxJava to get tasks
        val queryButton: Button = findViewById(R.id.QueryButton)
        queryButton.setOnClickListener {
            disposable.add(
                taskViewModel.getAllTasksRx()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { tasks ->
                        // Update UI with tasks
                        adapter.submitList(tasks)
                        tasks.forEach { task ->
                            Log.d(
                                "TaskViewModel",
                                "ID:${task.id}, Name: ${task.name}, Description: ${task.description}, Completed: ${task.isCompleted}"
                            )
                        }
                    }
            )
        }
        // Example button to clear all tasks
        val clearButton: Button = findViewById(R.id.clearButton)
        clearButton.setOnClickListener {
            taskViewModel.deleteAllTasks()
        }

        val insertButton: Button = findViewById(R.id.insertButton)
        insertButton.setOnClickListener {
            taskViewModel.insert(Task(3, "Task 3", "Description for Task 3", true));
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}
