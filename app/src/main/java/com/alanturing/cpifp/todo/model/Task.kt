package com.alanturing.cpifp.todo.model

import java.io.Serializable

data class Task(
    var id:Int,
    val title:String,
    val description:String,
    var isCompleted: Boolean = false): Serializable
