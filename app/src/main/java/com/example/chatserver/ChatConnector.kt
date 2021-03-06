import android.util.Log
import com.example.chatserver.App
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.parse
import java.io.PrintStream
import java.io.PrintWriter
import java.net.Socket
import java.nio.channels.SocketChannel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Scanner


 //Created by Roope

class ChatConnector : Runnable , ChatConnectorObservable {

    private val observers = mutableSetOf<ChatConnectorObserver>()

    override fun registerObserver(observer: ChatConnectorObserver) {
        observers.add(observer)
    }

    override fun deregisterObserver(observer: ChatConnectorObserver) {
        observers.remove(observer)
    }

    override fun notifyObservers(message: ChatMessage) {
        observers.forEach{it.newMessage(message)}
    }

    //Getting the input from the socket
    private lateinit var printStream: PrintStream

    //This function is called whenever a new message is received by the server.
    //Prints the message to every observer.
    @UnstableDefault
    fun sendMessage(message: ChatMessage) {
        val messageObjectJson = Json.stringify(ChatMessage.serializer(), message)
        printStream.println(messageObjectJson) // goes to buffer
        //.flush() // pushes it from buffer
    }


    @UnstableDefault
    override fun run() {
        //While method for reading the new inputs from input stream
        //Log for message
        //Json message being parsen
        //notifying observers with the new message
        //For phone 127.0.0.1/emulator 10.0.2.2
        val socket = Socket("10.0.2.2", 30001)
        val scanner1 = Scanner(socket.getInputStream())
        printStream = PrintStream(socket.getOutputStream(), true)
        while (true){
            val message = scanner1.nextLine()
            Log.d("Tag", message)
            val messageparse = Json.parse(ChatMessage.serializer(), message)
            notifyObservers(messageparse)
        }

    }
}