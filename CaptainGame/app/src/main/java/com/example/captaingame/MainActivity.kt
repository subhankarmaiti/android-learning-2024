package com.example.captaingame

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.captaingame.ui.theme.CaptainGameTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CaptainGameTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                CaptainGame()
                }
            }
        }
    }
}

@Composable
fun CaptainGame() {
    val treasuresFound = remember {
        mutableStateOf(0)
    }
    val direction = remember {
        mutableStateOf("North")
    }

    val stormOrTreasure = remember {
        mutableStateOf("")
    }

    Column {
        Text(text = "Treasures Found: ${treasuresFound.value}")
        Text(text = "Current Direction: ${direction.value}")
        Text(text = stormOrTreasure.value)
        Button(onClick = {
            direction.value = "East"
            if(Random.nextBoolean()) {
                treasuresFound.value +=1
                stormOrTreasure.value = "Found a Treasure!"
            }else {
                stormOrTreasure.value = "Storm Ahead!"
            }
        }) {
            Text(text = "Sail East")
        }
        Button(onClick = {
            direction.value = "West"
            if(Random.nextBoolean()) {
                treasuresFound.value +=1
                stormOrTreasure.value = "Found a Treasure!"
            }else {
                stormOrTreasure.value = "Storm Ahead!"
            }
        }) {
            Text(text = "Sail West")
        }
        Button(onClick = {
            direction.value = "North"
            if(Random.nextBoolean()) {
                treasuresFound.value +=1
                stormOrTreasure.value = "Found a Treasure!"
            }else {
                stormOrTreasure.value = "Storm Ahead!"
            }
        }) {
            Text(text = "Sail North")
        }
        Button(onClick = {
            direction.value = "South"
            if(Random.nextBoolean()) {
                treasuresFound.value +=1
                stormOrTreasure.value = "Found a Treasure!"
            }else {
                stormOrTreasure.value = "Storm Ahead!"
            }
        }) {
            Text(text = "Sail South")
        }

    }

}