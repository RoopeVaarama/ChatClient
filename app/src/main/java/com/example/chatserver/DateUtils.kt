package com.example.chatserver

import java.text.SimpleDateFormat
import java.util.*

//Singleton to handle formatting time in millis to hour and minutes
object DateUtils {
    fun fromMillisToTimeString(millis: Long) : String {
        val format = SimpleDateFormat("hh:mm a", Locale.getDefault())
        return format.format(millis)
    }
}