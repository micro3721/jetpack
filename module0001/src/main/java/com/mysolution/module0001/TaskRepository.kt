package com.mysolution.module0001;

/**
 * Author: github.com/micro3721
 * E-mail: mysolution@qq.com
 * I provide software outsourcing services
 * 我提供软件定制开发服务,欢迎垂询
 * Created on 2024/5/25
 * Describe:
 */
import androidx.lifecycle.LiveData
import io.reactivex.rxjava3.core.Single

class TaskRepository(private val taskDao: TaskDao) {
    val allTasks: LiveData<List<Task>> = taskDao.getAllTasks()

    suspend fun insert(task: Task): Long {
        return taskDao.insert(task)
    }

    suspend fun update(task: Task) {
        taskDao.update(task)
    }

    suspend fun delete(task: Task) {
        taskDao.delete(task)
    }

    suspend fun getTaskById(id: Int): LiveData<Task?> {
        return taskDao.getTaskById(id)
    }

    suspend fun deleteAll() {
        taskDao.deleteAll()
    }

    fun getAllTasksRx(): Single<List<Task>> {
        return taskDao.getAllTasksRx()
    }
}