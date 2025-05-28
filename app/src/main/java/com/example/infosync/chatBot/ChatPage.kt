package com.example.infosync.chatBot

import android.content.res.Configuration
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.infosync.chatBot.model.ChatViewModel
import com.example.a2.model.MessageModel
import com.example.infosync.R
import com.example.infosync.chatBot.theme.ColorModelDarkMessage
import com.example.infosync.chatBot.theme.ColorModelLightMessage
import com.example.infosync.chatBot.theme.ColorUserDarkMessage
import com.example.infosync.chatBot.theme.ColorUserLightMessage
import com.example.infosync.news.presentation.Dimens.MediumPadding1

@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
@Composable
fun ChatPage(modifier: Modifier = Modifier, viewModel: ChatViewModel) {
    Column(
        modifier = modifier.background(if(isSystemInDarkTheme()) colorResource(R.color.dark_background) else colorResource(R.color.chatBot_background))
    ) {
        AppHeader()
        MessageList(
            modifier = Modifier.weight(1f),
            messageList = viewModel.messageList
        )
        MessageInput(
            onMessageSend = {
                viewModel.sendMessage(it)
            }
        )
    }
}

@Composable
fun MessageList(modifier: Modifier = Modifier, messageList: List<MessageModel>) {
    if (messageList.isEmpty()) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(
                    if (isSystemInDarkTheme()) colorResource(R.color.dark_background) else colorResource(
                        R.color.chatBot_background
                    )
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.chatbot),
                contentDescription = "chatbot image",
                modifier = Modifier.size(150.dp)
            )
            Text(
                text = "Ask me anything",
                fontSize = 24.sp,
                color = if(isSystemInDarkTheme()) Color.White else Color.Black,
            )
        }
    } else {
        LazyColumn(
            modifier = modifier
                .background(Color.Transparent),
            reverseLayout = true
        ) {
            items(messageList.reversed()) {
                MessageRow(messageModel = it)
            }
        }
    }
}

@Composable
fun MessageRow(messageModel: MessageModel) {
    val isModel = messageModel.role == "model"

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
        ) {
            if (!isSystemInDarkTheme()) {
                Box(
                    modifier = Modifier
                        .align(if (isModel) Alignment.BottomStart else Alignment.BottomEnd)
                        .padding(
                            start = if (isModel) 8.dp else 70.dp,
                            end = if (isModel) 70.dp else 8.dp,
                            top = 8.dp,
                            bottom = 8.dp
                        )
                        .clip(RoundedCornerShape(48f))
                        .background(if (isModel) ColorModelLightMessage else ColorUserLightMessage)
                        .padding(16.dp)
                ) {
                    SelectionContainer {
                        Text(
                            text = messageModel.message,
                            fontWeight = FontWeight.W500,
                            color = if(isSystemInDarkTheme()) Color.Black else Color.White
                        )
                    }
                }
            }
            else{
                Box(
                    modifier = Modifier
                        .align(if (isModel) Alignment.BottomStart else Alignment.BottomEnd)
                        .padding(
                            start = if (isModel) 8.dp else 70.dp,
                            end = if (isModel) 70.dp else 8.dp,
                            top = 8.dp,
                            bottom = 8.dp
                        )
                        .clip(RoundedCornerShape(48f))
                        .background(if (isModel) ColorModelDarkMessage else ColorUserDarkMessage)
                        .padding(16.dp)
                ) {
                    SelectionContainer {
                        Text(
                            text = messageModel.message,
                            fontWeight = FontWeight.W500,
                            color = if (isSystemInDarkTheme()) Color.Black else Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MessageInput(onMessageSend: (String) -> Unit) {

    var message by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.Transparent),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            modifier = Modifier.weight(1f),
            value = message,
            onValueChange = { message = it },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = if (isSystemInDarkTheme())Color.White else Color.Black,
                unfocusedBorderColor = if (isSystemInDarkTheme()) colorResource(R.color.shimmer) else colorResource(R.color.body),
                cursorColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
                focusedTextColor = if (isSystemInDarkTheme()) colorResource(R.color.placeholder) else colorResource(R.color.bodyBackground),
                unfocusedTextColor = if (isSystemInDarkTheme()) colorResource(R.color.chatBot_background) else colorResource(R.color.body),
            ),
            placeholder = {
                Text(
                    text = "Type your message...",
                    color = if (isSystemInDarkTheme()) Color.White else Color.Black
                )
            }
        )
        IconButton(
            colors = IconButtonColors(
                contentColor = Color.Black,
                containerColor = Color.Transparent,
                disabledContentColor = Color.Black,
                disabledContainerColor = Color.Black
            ),
            onClick = {
                if (message.isNotEmpty()) {
                    onMessageSend(message)
                    message = ""
                }
            }
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Send,
                contentDescription = "Send",
                tint = if (isSystemInDarkTheme()) Color.White else Color.Black
            )
        }
    }
}

@Composable
fun AppHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(4.dp)
            .statusBarsPadding()
    ) {
        Image(
            painterResource(id = R.drawable.ic_logo),
            contentDescription = null,
            modifier = Modifier
                .width(160.dp)
                .height(50.dp)
                .padding(horizontal = MediumPadding1)
        )

    }
}

@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ChatPagePreview(){

    val chatViewModel: ChatViewModel = hiltViewModel()

    ChatPage(
        viewModel = chatViewModel
    )

}