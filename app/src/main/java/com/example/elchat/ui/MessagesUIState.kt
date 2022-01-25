package com.example.elchat.ui

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.Provides
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class MessagesUIState(val channel: String,
val members: Int) {
    private val _messages: MutableList<Message> = mutableStateListOf()

    init {
        val startMessages = IntRange(1,5).map {
            Message(author = "me",
                content =
                """This is a very long Message.
            |The id of the message will be like this $it.
            |This is the other line""".trimMargin()
                , timeStamp = "3:45 pm"
            )
        }
        startMessages.forEach{
            addMessage(it)
        }
    }

    val messages: List<Message> = _messages

    fun addMessage(msg: Message){
        _messages.add(0, msg)
    }
}

@Immutable
data class Message(
    val author: String,
    val content: String,
    val timeStamp: String,
    val image: Int? = null,
    val authorImage: Int = if (author == "me") android.R.drawable.star_big_on else android.R.drawable.star_big_off
)