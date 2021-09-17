package com.example.todolist

//a Data Class's primary purpose is just to hold data, no logic, single constructor
//this class represents a To do Object, which has a string and a checkbox that defaults to empty
data class Todo(
    val title: String,
    var isChecked: Boolean = false
)