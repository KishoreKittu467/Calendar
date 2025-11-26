package com.kkapps.bubbles.app.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kkapps.common.ui.components.icons.ArrowBackIcon
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventEditorScreen(onBack: () -> Unit) {
    val viewModel = koinViewModel<EventEditorViewModel>()
    var name by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var start by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("New Event") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = ArrowBackIcon, contentDescription = "Back")
                    }
                },
                actions = {
                    TextButton(
                        onClick = {
                            if (name.isNotBlank()) {
                                val startTime = start.toLongOrNull()
                                val durationMs = duration.toLongOrNull() ?: 3600000L
                                viewModel.saveEvent(name, notes, startTime, durationMs, onBack)
                            }
                        }
                    ) {
                        Text("Save")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)
        ) {
            OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
            OutlinedTextField(value = notes, onValueChange = { notes = it }, label = { Text("Notes") })
            OutlinedTextField(value = start, onValueChange = { start = it }, label = { Text("Start (epoch millis)") })
            OutlinedTextField(value = duration, onValueChange = { duration = it }, label = { Text("Duration (ms)") })
        }
    }
}