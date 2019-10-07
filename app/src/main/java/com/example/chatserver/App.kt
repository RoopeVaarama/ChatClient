package com.example.chatserver

import android.app.Application

//Class for storing the users name
class App:Application() {
    companion object
    lateinit {
        var user: String = ""
    }
}
