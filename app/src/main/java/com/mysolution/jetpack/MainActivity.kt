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
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var taskViewModel: TaskViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TaskListAdapter

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
        insertInitialTasks()

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


    private fun insertInitialTasks() {
        GlobalScope.launch {
            taskViewModel.insert(Task(0, "Task 1", "Description for Task 1", false))
            taskViewModel.insert(Task(1, "Task 2", "Description for Task 2", true))
            taskViewModel.insert(Task(2, "Task 3", "Description for Task 3", false))
        }
    }
}
