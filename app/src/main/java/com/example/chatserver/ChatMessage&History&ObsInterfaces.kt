import kotlinx.serialization.Serializable

/**
 * Created by Roope
 */


/**
 * Chat message is the basic message class.
 */
@Serializable
class ChatMessage(var message: String, var username : String){
    override fun toString(): String {
        return message + username
    }
}


interface ChatConnectorObservable {
    fun registerObserver(observer:ChatConnectorObserver)
    fun deregisterObserver(observer:ChatConnectorObserver)
    fun notifyObservers (message:ChatMessage)
}

interface ChatConnectorObserver {
    fun newMessage(message:ChatMessage)

}












/*object ChatConsole : ChatConnectorObserver {
    override fun newMessage(message: ChatMessage) {
        println(message)
    }
}





/**
 * ChatHistory holds a list of the entire chat history and observers that
 * observe it's changes.

object ChatHistory : ChatConnectorObserver {


fun insert(message: ChatMessage) {
listOfChatMessages.add(message)
notifyObservers(message)
}



private val listOfChatMessages = mutableListOf<ChatMessage>()

//Returns the entire chat history as a single string
override fun toString(): String {
var chatHistory = ""
for (chatMessage in istOfChatMessages) {
chatHistory += (chatMessage.message) + "\n"
}
return chatHistory
}
}**/