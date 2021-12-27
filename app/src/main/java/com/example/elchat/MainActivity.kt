package com.example.elchat

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.elchat.ui.theme.ElChatTheme
import java.util.*

const val APP_TAG = "El-Chat"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ElChatTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    ElChatChatScreen()
                }
            }
        }
    }
}

@Composable
fun ElChatChatScreen(){
    val startMessages = IntRange(1,5).map {
        Message(author = "me",
        content =
        """This is a very long Message. 
            |The id of the message will be like this $it.
            |This is the other line""".trimMargin()
        , timeStamp = "3:45 pm"
        )

    }
    val messagesState = MessagesUIState(channel = "Channel 1", members = 2, startMessages)
    Column(modifier = Modifier.fillMaxSize().padding(2.dp), Arrangement.SpaceBetween) {
        LazyColumn(modifier = Modifier.weight(1f), reverseLayout = true) {
            items(items=messagesState.messages) {
                ElChatChatMessage(authorName = it.author,
                    authorImage = it.authorImage,
                    dateSent = it.timeStamp,
                    onUserPhotoClick = {
                        Log.d(APP_TAG, "photo clicked")
                    },
                    content = {
                        ElChatChatText(text = it.content,
                            image = "https://cdn.icon-icons.com/icons2/2235/PNG/512/android_os_logo_icon_134673.png")},
                )
                Spacer(modifier = Modifier.padding(2.dp))
            }
        }
        ElChatSendMessageBar({
                Log.d(APP_TAG,"Text received from input: $it")
                messagesState.addMessage(Message("me", it, Date().toString()))
        },modifier = Modifier.fillMaxWidth()
            .background(MaterialTheme.colors.background))
    }
}

@Composable
fun ElChatChatText(text: String, image: String = ""){
    val annotatedText = remember(text) { text }
    Box(modifier = Modifier.fillMaxWidth()) {
        Column{
            Text(annotatedText, style = typography.body1, modifier = Modifier.fillMaxWidth())
        }
        Image(painter = rememberImagePainter(image), contentDescription = "Image",
        modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun ElChatChatMessage(authorName: String,
                      authorImage: Int,
                      dateSent: String,
                      onUserPhotoClick: ()-> Unit,
                      content: @Composable ()-> Unit) {
    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically){
        ElChatUserPhoto(image = authorImage, onUserPhotoClick)
        Column(modifier = Modifier.padding(PaddingValues(5.dp, 1.dp))
            ) {
            Row {
                Text(text = "From: $authorName", )
                Spacer(modifier = Modifier.padding(5.dp))
                Text(text = dateSent)
            }
            Surface(modifier = Modifier.align(Alignment.End)
                .background(Color.Blue)
                .clip(RoundedCornerShape(2.dp))){
                content()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ElChatTheme {
        ElChatChatScreen()
    }
}