import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import java.io.PrintWriter
import java.net.Socket
import java.nio.channels.SocketChannel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Scanner

/**
 * Created by Roope
 */
class ChatConnector(s: Socket) : Runnable , ChatHistoryObserver {

    //Getting the input and output streams from the socket
    private val printStream = PrintWriter(s.getOutputStream())

    private lateinit var socket: Socket
    private lateinit var scanner1: Scanner
    var user: String = " "
    //This function is called whenever a new message is received by the server.
    //Prints the message to every observer.
    @UnstableDefault
    override fun newMessage(message: ChatMessage) {
        val messageObjectJson = Json.stringify(ChatMessage.serializer(), message)
        printStream.println(messageObjectJson) // goes to buffer
        printStream.flush() // pushes it from buffer
    }


    override fun run() {

        /**
         * The next block asks the client for an username and checks if it is not in use.
         * ====================================================================
         */
        socket = Socket("10.0.1.1", 30001)
        scanner1 = Scanner(socket.getInputStream())

        printStream.println("Insert username")
        printStream.flush()
    }
}