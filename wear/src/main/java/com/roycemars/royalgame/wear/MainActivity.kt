package com.roycemars.royalgame.wear

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.wear.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.TextField

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Search()
        }
    }
}

@Composable
fun Search() {
    val text = remember { mutableStateOf("") }
    MaterialTheme {
        TextField(value = text.value, onValueChange = { text.value = it }, label = { Text("Search clubs") })
    }
}

@Preview
@Composable
fun Preview() {
    Search()
}
