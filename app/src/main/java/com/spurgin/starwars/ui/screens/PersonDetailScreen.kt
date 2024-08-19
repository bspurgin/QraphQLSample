package com.spurgin.starwars.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.spurgin.starwars.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonDetailScreen(
    navController: NavController,
    personId: String,
    mainViewModel: MainViewModel
) {
    val person = mainViewModel.selectedPerson.collectAsState().value
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Person Detail") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }, containerColor = Color.Transparent
    ) { paddingValues ->
        Column(Modifier.padding(paddingValues).padding(32.dp)) {

            person?.let {
                val annotatedText = buildAnnotatedString {
                    append("Click ")
                    withStyle(style = SpanStyle(color = Color.Blue)) {
                        append("here")
                    }
                    append(" to view homeworld data for ${it.name}")
                }
                Text(
                    text = annotatedText,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.clickable {
                        showBottomSheet = true
                    }
                )
            }
        }

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState
            ) {
                Column(
                    Modifier
                        .padding(16.dp)
                        .height(200.dp)
                ) {
                    Text(
                        text = "Name: ${person?.name}",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Homeworld: ${person?.homeworld?.name ?: "Unknown"}"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Created: ${person?.homeworld?.created}")
                }
            }
        }
    }
    //todo this is called
    LaunchedEffect(personId) {
        mainViewModel.fetchPersonDetail(personId)
    }
}
