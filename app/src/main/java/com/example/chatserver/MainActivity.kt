package com.example.chatserver

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import java.net.Socket

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //val s = Socket(10.0.2.2 , 30001)

    }

    fun login(view: View){
        val chat = Intent(this,ChatActivity::class.java)
        startActivity(chat)
    }
}
