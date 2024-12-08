package com.example.todoapp.pack_interface

import com.example.todoapp.Model.Task

interface OnClickListener {
    fun onclick(
        position: Int,
        task: Task

    )
}