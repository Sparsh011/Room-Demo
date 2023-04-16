package com.example.roomdemo.activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.roomdemo.R
import com.example.roomdemo.database.Database
import com.example.roomdemo.database.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = Database.getDatabase(applicationContext)
        val userDao = database.userDao()

        val etUserName = findViewById<EditText>(R.id.et_username)
        val etFirstName = findViewById<EditText>(R.id.et_first_name)
        val etLastName = findViewById<EditText>(R.id.et_last_name)
        val addToDatabase = findViewById<Button>(R.id.btn_add_to_database)

        addToDatabase.setOnClickListener{
            val userName = etUserName.text.toString()
            val firstName = etFirstName.text.toString()
            val lastName = etLastName.text.toString()



                val user = User(userName, firstName, lastName)

                GlobalScope.launch {
                    if (userDao.count(userName) != 0){
                        withContext(Dispatchers.Main) {
                            Toast.makeText(applicationContext, "User Already Exists!", Toast.LENGTH_SHORT).show()
                        }
                        return@launch
                    }
                    userDao.insert(user)
                }

        }

        val getData = findViewById<Button>(R.id.btn_get_from_database)

        getData.setOnClickListener{
            GlobalScope.launch {
                userDao.getAll().collect { users ->
                    Log.d("User Data -> ", "Users: $users")
                }
            }

        }
    }
}