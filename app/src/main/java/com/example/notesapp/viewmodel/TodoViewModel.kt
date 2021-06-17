package com.example.notesapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.notesapp.model.Todo
import com.example.notesapp.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(private val todoRepository: TodoRepository): ViewModel() {

    fun insertTodo(todo: Todo) = viewModelScope.launch {
        todoRepository.insertTodoDao(todo)
    }

    val getAllTodoList = todoRepository.getAllTodoList().asLiveData()

    fun deleteTodoList(todo: Todo) = viewModelScope.launch {
        todoRepository.deleteTodoList(todo)
    }
}