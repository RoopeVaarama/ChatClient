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