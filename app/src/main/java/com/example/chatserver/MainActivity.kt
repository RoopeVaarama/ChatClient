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

    }
    //onCreate for Options menu in android
   override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
       menuInflater.inflate(R.menu.mymenu, menu)
       return true
    }
    //onClicklistener for Options menu items
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.menuitem -> {
                darkmode()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    //Function that is called in menuitems click and changes the app to dark mode and light mode
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
    //Function when login is being clicked to take the username from the inputfield to a data class where the current user for app is being stored
    //If username field is empty it toast that you need to insert one and stops there
    //If there is a username it is inserted to App() class in to user
    //And last thing is a intento to start a new activity
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