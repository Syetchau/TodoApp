package com.example.notesapp.db

import androidx.room.*
import com.example.notesapp.model.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: Todo)

    @Query("SELECT * FROM todo ORDER BY todoTitle ASC")
    fun getAllTodoList(): Flow<List<Todo>>

    @Delete
    suspend fun deleteTodo(todo: Todo)
}