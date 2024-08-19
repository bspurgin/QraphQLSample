package com.spurgin.starwars

import StarWarsApp
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import com.spurgin.starwars.ui.theme.StarWarsTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Log.d("ZZZ", " setting content")

            StarWarsTheme {
                StarWarsApp()
            }
        }
    }
}



