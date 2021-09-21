package com.example.todolist

import android.media.CamcorderProfile.get
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.reflect.Array.get


class MainActivity : AppCompatActivity(), TodoAdapter.OnItemClickListener {

    private lateinit var todoAdapter: TodoAdapter //a promise to be initializaed later (lateinit)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoAdapter = TodoAdapter(mutableListOf(), this) //creates new todoadapter, passing empty list by default

        rvTodoItems.adapter = todoAdapter
        rvTodoItems.layoutManager = LinearLayoutManager(this) //defines how items are arranged (layout manager)

        btnAddTodo.setOnClickListener {
            rvTodoItems.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL)) //adds lines in between new entry
            val todoTitle = etTodoTitle.text.toString()
            if(todoTitle.isNotEmpty()) {
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                etTodoTitle.text.clear()
            }
        }

        btnDeleteDoneTodos.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }


        darkmode.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "Item $position clicked", Toast.LENGTH_SHORT).show()
        todoAdapter.notifyItemChanged(position)
    }

}