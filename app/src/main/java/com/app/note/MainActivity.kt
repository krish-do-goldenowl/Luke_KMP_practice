package com.app.note

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Navigation()
        }
    }
}

//@Composable
//fun Greeting( modifier: Modifier = Modifier) {
//    Column {
//        Text(
//            "Hello",
//            modifier = modifier
//        )
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    NoteAppDemoTheme {
//        Greeting()
//    }
//}