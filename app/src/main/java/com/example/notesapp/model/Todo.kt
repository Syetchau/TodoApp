package com.example.notesapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class Todo(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val todoTitle: String
)


