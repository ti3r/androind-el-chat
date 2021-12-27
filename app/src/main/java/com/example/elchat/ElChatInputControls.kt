package com.example.elchat

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.elchat.ui.theme.ElChatTheme


@Composable
fun ElChatSendButton(mvc: ()-> Unit,
                     modifier: Modifier = Modifier) {
    Button( { mvc() },
        content = { Text("Send!!!!") }
    , shape = RoundedCornerShape(5.dp)
    , modifier = modifier
    )
}

@Composable
fun ElChatSendMessageBar(onSent: (text: String)-> Unit, modifier: Modifier = Modifier) {
    var text = remember {
        mutableStateOf("")
    }
    var mode by remember { mutableStateOf("Text")}
    Box(modifier = modifier.fillMaxWidth().padding(2.dp)) {
        ElChatChatInput(text, modifier.align(Alignment.TopStart).fillMaxWidth())
        ElChatSendButton({
            onSent(text.value)
            text.value = ""
                         },
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}

@Composable
fun ElChatChatInput(text: MutableState<String>, modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = text.value,
            onValueChange = { text.value = it },
            modifier = modifier.fillMaxWidth()
        )
        Icon(
            Icons.Outlined.PlayArrow, contentDescription = "",
            modifier = Modifier.align(Alignment.TopEnd)
        )
    }
}



@Preview(showBackground = true)
@Composable
fun InputPreview() {
    ElChatTheme {
        ElChatSendMessageBar(
            {
                    print("Text received from input: $it")
            }
            , modifier = Modifier.fillMaxWidth()
        )
    }
}