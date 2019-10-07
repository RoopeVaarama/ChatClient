package com.example.chatserver

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_main.*
import java.net.ServerSocket
import java.net.Socket
import java.net.SocketAddress

class MainActivity : AppCompatActivity() {


    private var username: String = " "
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        val switch = findViewById<Switch>(R.id.modeSwitch)
        switch.setOnClickListener(View.OnClickListener {
            if(switch.isChecked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        })

    }
        fun login(view: View) {
            val inputField = findViewById<TextView>(R.id.nameView)
            username = inputField.text.toString()
            if (username.isEmpty()) {
                Toast.makeText(
                    applicationContext,
                    "Username should not be empty",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                App.user = username
                Log.d("asd", username)
                val chat = Intent(this, ChatActivity::class.java)
                Toast.makeText(applicationContext, "Login successful!", Toast.LENGTH_SHORT).show()
                startActivity(chat)
            }
        }
    }