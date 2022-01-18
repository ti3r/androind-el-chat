package com.example.elchat

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.elchat.ui.ElChatChatInput
import com.example.elchat.ui.ElChatChatScreen
import com.example.elchat.ui.theme.ElChatTheme

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.elchat", appContext.packageName)
    }

    @Test
    fun testSendButtonClick() {
        composeTestRule.setContent {
            ElChatTheme {
                ElChatChatScreen()
            }
        }
        composeTestRule.onNodeWithTag("SendButton").performClick()
    }

    @Test
    fun testInputTextField() {
        val fakeState = mutableStateOf("")
        composeTestRule.setContent {
            ElChatTheme {
                ElChatChatInput(text = fakeState)
            }
        }
        composeTestRule.onNodeWithTag("InputTextField").performTextInput("Hola Mundo!!")
        assert(fakeState.value == "Hola Mundo!!"){
            fail("Incorrect value for InputTextField")
        }
    }

}

