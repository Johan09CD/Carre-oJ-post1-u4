package com.universidad.taskmanager.data

import com.universidad.taskmanager.data.local.TaskDao
import com.universidad.taskmanager.data.local.TaskEntity
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val dao: TaskDao) {
    // Observa todas las tareas en tiempo real [cite: 150]
    fun getAllTasks(): Flow<List<TaskEntity>> = dao.observeAll()

    // Agrega una nueva tarea [cite: 153, 154]
    suspend fun addTask(title: String, description: String) {
        dao.insert(TaskEntity(title = title, description = description))
    }

    // Cambia el estado de completado [cite: 155, 156]
    suspend fun toggleComplete(id: Int, completed: Boolean) {
        dao.toggleComplete(id, completed)
    }

    // Elimina una tarea [cite: 157]
    suspend fun deleteTask(task: TaskEntity) = dao.delete(task)
}