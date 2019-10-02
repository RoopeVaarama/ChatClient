package com.example.chatserver

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.net.ServerSocket
import java.net.Socket
import java.net.SocketAddress

class MainActivity : AppCompatActivity() {


    var chat = ChatActivity()
    var username: String = " "
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun login(view: View){
        val inputField = findViewById<TextView>(R.id.nameView)
        username = inputField.text.toString()
        chat.user.add(username)
        Log.d("asd", username)
        val chat = Intent(this,ChatActivity::class.java)
        startActivity(chat)
    }
}