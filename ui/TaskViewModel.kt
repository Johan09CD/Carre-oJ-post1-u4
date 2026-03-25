package com.universidad.taskmanager.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.universidad.taskmanager.data.TaskRepository
import com.universidad.taskmanager.data.local.TaskEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskViewModel(private val repo: TaskRepository) : ViewModel() {

    // Expone los datos mediante StateFlow [cite: 18, 174, 175]
    val tasks = repo.getAllTasks()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addTask(title: String, description: String) {
        viewModelScope.launch { repo.addTask(title, description) }
    }

    fun toggleComplete(task: TaskEntity) {
        viewModelScope.launch {
            repo.toggleComplete(task.id, !task.isCompleted)
        }
    }

    fun deleteTask(task: TaskEntity) {
        viewModelScope.launch { repo.deleteTask(task) }
    }
}