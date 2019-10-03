package com.example.chatserver

import ChatConnector
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.net.ServerSocket
import java.net.Socket
import java.net.SocketAddress

class MainActivity : AppCompatActivity() {



    var username: String = " "
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun login(view: View){
        val inputField = findViewById<TextView>(R.id.nameView)
        username = inputField.text.toString()
        if(username.isEmpty()){
            Toast.makeText(applicationContext,"Username should not be empty", Toast.LENGTH_SHORT).show()
        } else {
        User.user = username
        Log.d("asd", username)
        val chat = Intent(this,ChatActivity::class.java)
        startActivity(chat)
        }
    }
}