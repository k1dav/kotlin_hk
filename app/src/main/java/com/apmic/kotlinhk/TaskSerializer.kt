package com.apmic.kotlinhk

import kotlinx.serialization.Serializable

@Serializable
data class Task(
    val id: Int? = null,
    val title: String,
    val description: String = "",
    val done: Boolean? = null
)

@Serializable
data class FromTask(
    val tasks: List<Task>? = null,
    val task: List<Task>? = null
)