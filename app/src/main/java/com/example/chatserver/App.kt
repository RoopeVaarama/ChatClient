package com.example.chatserver

import android.annotation.SuppressLint
import android.app.Application

//Class for storing the users name and the check boolean status for DarkMode
@SuppressLint("Registered")
class App:Application() {
    companion object
    lateinit {
        var user: String = ""
        var check: Boolean = false
    }
}
