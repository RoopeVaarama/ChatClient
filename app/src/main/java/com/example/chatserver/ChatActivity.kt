package com.example.chatserver

import ChatConnector
import ChatConnectorObserver
import ChatMessage
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.serialization.UnstableDefault
import java.util.*




class ChatActivity : AppCompatActivity(), ChatConnectorObserver {


    private val chatconnector = ChatConnector()
    private lateinit var adapter: MessageAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MessageAdapter(this)
        recyclerView.adapter = adapter
        chatconnector.registerObserver(this)
        Thread(chatconnector).start()

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
    //When new input is clicked it's being read here and send to chatconnector in a new threat
    // also the inputfield is being cleared after that
    @UnstableDefault
    fun newInput(view: View){

        val inputField = findViewById<TextView>(R.id.messageView)
        Thread {
            chatconnector.sendMessage(ChatMessage(inputField.text.toString(), App.user))
            Log.d("add", inputField.text.toString() + App.user)
            inputField.text = ""
        }.start()
    }
    //When new message is received this method takes the current time in millis and adds it to the message2 that is in form Message
    //New uithread adds the message to recyclerviews adapter and scroll the view to the latest message
    //Latest message is shown in to bottom part of the recyclerview
    override fun newMessage(message: ChatMessage) {
        val time = Calendar.getInstance().timeInMillis
        val message2 = Message(message.message, message.username, time)
        runOnUiThread {
        adapter.addMessage(message2)
        recyclerView.scrollToPosition(adapter.itemCount-1)
        }
    }

}