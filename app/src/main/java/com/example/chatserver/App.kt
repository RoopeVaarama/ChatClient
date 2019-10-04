package com.example.chatserver

import android.app.Application

class App:Application() {
    companion object
    lateinit {
        var user: String = ""
    }
}
