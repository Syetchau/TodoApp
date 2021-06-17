package com.example.notesapp.utils

import com.example.notesapp.model.Todo

interface OnClickListener {
    fun onClick(todo: Todo, position: Int)
}