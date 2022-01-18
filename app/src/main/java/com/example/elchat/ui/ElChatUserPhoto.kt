package com.example.elchat.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.elchat.ui.theme.ElChatTheme
import kotlin.random.Random


fun randomColor() = run {
    Color(red = Random.nextInt(0, 255),
        blue = Random.nextInt(0, 255),
        green = Random.nextInt(0, 255))
}

@Composable
fun ElChatUserPhoto(image: Int,
                    onUserPhotoClick: ()-> Unit,
                    modifier: Modifier = Modifier) {
    val borderColor = remember { randomColor() }
    Image(painter = painterResource(image),
        contentDescription = "image",
        modifier= modifier
            .border(2.dp, borderColor, CircleShape)
            .padding(4.dp)
            .clip(CircleShape)
            .size(38.dp)
            .clickable { onUserPhotoClick() }
    )
}


@Preview(showBackground = true)
@Composable
fun ChatImagePreview() {
    ElChatTheme {
        ElChatUserPhoto(android.R.drawable.star_big_on,
            {print("Photo clicked")})
    }
}