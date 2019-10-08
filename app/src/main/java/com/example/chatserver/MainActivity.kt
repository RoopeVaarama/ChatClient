package com.example.chatserver

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {


    private var username: String = " "
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

    }

   override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
       menuInflater.inflate(R.menu.mymenu, menu)
       return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.menuitem -> {
                darkmode()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    fun darkmode() {
        if (App.check) {
            App.check = false
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {
            Log.d("chek", App.check.toString())
            App.check = true
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
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