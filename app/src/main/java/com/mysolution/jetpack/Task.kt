package com.mysolution.jetpack;

/**
 * Author: github.com/micro3721
 * E-mail: mysolution@qq.com
 * I provide software outsourcing services
 * 我提供软件定制开发服务,欢迎垂询
 * Created on 2024/5/25
 * Describe:
 */
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val description: String,
    val isCompleted: Boolean
)
