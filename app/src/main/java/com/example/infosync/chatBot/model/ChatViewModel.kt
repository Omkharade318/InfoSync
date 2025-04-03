package com.example.infosync.chatBot.model

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2.model.MessageModel
import com.example.a2.util.Constants
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {

    // Use mutableStateListOf to make the list reactive
    val messageList = mutableStateListOf<MessageModel>()

    val generativeModel: GenerativeModel = GenerativeModel(
        modelName = "gemini-2.0-flash",
        apiKey = Constants.API_KEY
    )

    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    fun sendMessage(question: String) {
        Log.d("ChatViewModel", "Sending message: $question")
        Log.d("ChatViewModel", "Before send: ${messageList.size} messages")

        viewModelScope.launch {
            try {
                val chat = generativeModel.startChat(
                    history = messageList.map {
                        content(it.role) { text(it.message) }
                    }
                )

                // Add user message and typing indicator
                messageList.add(MessageModel(question, "user"))
                messageList.add(MessageModel("Typing....", "model"))

                // Get response and update list
                val response = chat.sendMessage(question)
                messageList.removeLast() // Remove "Typing...."
                messageList.add(MessageModel(response.text.toString(), "model"))

                Log.d("ChatViewModel", "After send: ${messageList.size} messages")
            } catch (e: Exception) {
                messageList.removeLast()
                messageList.add(MessageModel("Error: ${e.message}", "model"))
            }
        }
    }
}