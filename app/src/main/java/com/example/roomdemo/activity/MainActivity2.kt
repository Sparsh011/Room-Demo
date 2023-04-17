package com.example.roomdemo.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdemo.R
import com.example.roomdemo.adapter.DataAdapter
import com.example.roomdemo.database.Database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val recyclerView = findViewById<RecyclerView>(R.id.rv_data)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = DataAdapter()
        val database = Database.getDatabase(applicationContext)
        val userDao = database.userDao()

        GlobalScope.launch {
            userDao.getAll().collect() {
                withContext(Dispatchers.Main){
                    adapter.differ.submitList(it)
                    return@withContext
                }
            }
        }

        recyclerView.adapter = adapter

    }
}