package ru.skillbranch.devintensive.models

import org.junit.Test
import ru.skillbranch.devintensive.extensions.TimeUnits
import ru.skillbranch.devintensive.extensions.add
import java.util.*

class BaseMessageTest {

    val user = User.makeUser("John White")

    @Test
    fun textMessage(){
        val message = BaseMessage.makeMessage(user, Chat("0"), Date(), "any text message", "text")
        println(message.formatMessage())
    }

    @Test
    fun imageMessage(){
        val message = BaseMessage.makeMessage(user, Chat("0"),
            Date().add(-2, TimeUnits.HOUR), "https://anyurl.com", "image", true)
        println(message.formatMessage())
    }
}