package com.mysolution.jetpack;

/**
 * Author: github.com/micro3721
 * E-mail: mysolution@qq.com
 * I provide software outsourcing services
 * 我提供软件定制开发服务,欢迎垂询
 * Created on 2024/5/25
 * Describe:
 */
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TaskRepository
    val allTasks: LiveData<List<Task>>
    private val disposable = CompositeDisposable()

    init {
        val taskDao = TaskDatabase.getDatabase(application).taskDao()
        repository = TaskRepository(taskDao)
        allTasks = repository.allTasks
    }

    fun insert(task: Task): Any = viewModelScope.launch {
        repository.insert(task)
    }

    fun update(task: Task) = viewModelScope.launch {
        repository.update(task)
    }

    fun delete(task: Task) = viewModelScope.launch {
        repository.delete(task)
    }

    fun deleteAllTasks() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }

    fun getAllTasksRx(): Single<List<Task>> {
        return repository.getAllTasksRx();
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    fun insertInitialTasks() {
        viewModelScope.launch {
            insert(
                Task(
                    name = "Task 1",
                    description = "Description for Task 1",
                    isCompleted = false
                )
            )
            insert(
                Task(
                    name = "Task 2",
                    description = "Description for Task 2",
                    isCompleted = true
                )
            )
            insert(
                Task(
                    name = "Task 3",
                    description = "Description for Task 3",
                    isCompleted = false
                )
            )

        }
    }
}