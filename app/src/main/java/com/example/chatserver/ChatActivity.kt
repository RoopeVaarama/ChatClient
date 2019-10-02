package com.example.chatserver

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_chat.*


class ChatActivity : AppCompatActivity() {


    var user: ArrayList<String> = ArrayList()
    private var messages: ArrayList<String> = ArrayList()
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    //var username: String = " "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        viewManager = LinearLayoutManager(this)
        viewAdapter = MyRecyclerViewAdapter(user, messages, this)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }
    }

    fun newMessage(view: View){
        val inputField = findViewById<TextView>(R.id.messageView)
        Log.d("add", "adding")
        user.add("Roope")
        messages.add(inputField.text.toString())
        viewAdapter.notifyDataSetChanged()
    }

}

class MyRecyclerViewAdapter(
    private val userName: List<String>,
    private val messages: List<String>,
    private val context: Context
) :
    RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val textviewname = itemView.findViewById<TextView>(R.id.name) as TextView
        val textviewmessage = itemView.findViewById<TextView>(R.id.message) as TextView

    }

    override fun onCreateViewHolder(vg: ViewGroup, vt: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context)
            .inflate(R.layout.name_recycler_view_item, vg, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(vh: MyViewHolder, pos: Int) {
        vh.textviewname.text = userName[pos]
        vh.textviewmessage.text = messages[pos]
    }

    override fun getItemCount(): Int {
        return userName.size
    }
}
