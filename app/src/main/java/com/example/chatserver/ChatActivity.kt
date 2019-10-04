package com.example.chatserver

import ChatConnector
import ChatConnectorObserver
import ChatMessage
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewManager
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.my_message.view.*
import kotlinx.android.synthetic.main.other_message.view.*
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.name_recycler_view_item.*
import kotlinx.serialization.UnstableDefault
import java.sql.Time
import java.util.*
import java.util.Calendar.getInstance
import kotlin.collections.ArrayList



class ChatActivity : AppCompatActivity(), ChatConnectorObserver {


    val chatconnector = ChatConnector()
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

    @UnstableDefault
    fun newInput(view: View){

        val inputField = findViewById<TextView>(R.id.messageView)
        Thread {
            chatconnector.sendMessage(ChatMessage(inputField.text.toString(), App.user))
            Log.d("add", inputField.text.toString() + App.user)
            inputField.text = ""
        }.start()
    }

    override fun newMessage(message: ChatMessage) {
        val time = Calendar.getInstance().timeInMillis
        val message2 = Message(message.message, message.username, time)
        runOnUiThread {
        adapter.addMessage(message2)
        recyclerView.scrollToPosition(adapter.itemCount -1)
        }
    }

}