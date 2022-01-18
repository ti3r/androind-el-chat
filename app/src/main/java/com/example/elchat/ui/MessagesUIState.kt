package com.example.elchat.ui

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateListOf

class MessagesUIState(val channel: String,
val members: Int,
initialMessages: List<Message>) {
    private val _messages: MutableList<Message> = mutableStateListOf(*initialMessages.toTypedArray())
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