package com.spurgin.starwars

import StarWarsApp
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import com.spurgin.starwars.api.ApolloClientInstance
import com.spurgin.starwars.repository.StarWarsRepository
import com.spurgin.starwars.repository.StarWarsRepositoryProvider

import com.spurgin.starwars.ui.theme.StarWarsTheme
import com.spurgin.starwars.viewmodel.MainViewModel
import com.spurgin.starwars.viewmodel.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            StarWarsTheme {
                StarWarsApp(mainViewModel)
            }
        }
    }
}



