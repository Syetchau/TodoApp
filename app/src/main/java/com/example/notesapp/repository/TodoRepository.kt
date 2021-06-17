package com.example.notesapp.repository

import com.example.notesapp.db.TodoDao
import com.example.notesapp.model.Todo
import javax.inject.Inject

class TodoRepository @Inject constructor(private val todoDao: TodoDao) {

    suspend fun insertTodoDao(todo: Todo) = todoDao.insertTodo(todo)

    fun getAllTodoList() = todoDao.getAllTodoList()

    suspend fun deleteTodoList(todo: Todo) = todoDao.deleteTodo(todo)
}