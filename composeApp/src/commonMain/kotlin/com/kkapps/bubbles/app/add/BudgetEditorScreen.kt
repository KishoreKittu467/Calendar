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
fun BudgetEditorScreen(onBack: () -> Unit) {
    val viewModel = koinViewModel<BudgetEditorViewModel>()
    var amount by remember { mutableStateOf("") }
    var payer by remember { mutableStateOf("") }
    var receiver by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("New Budget / Transaction") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = ArrowBackIcon, contentDescription = "Back")
                    }
                },
                actions = {
                    TextButton(
                        onClick = {
                            val amountFloat = amount.toFloatOrNull()
                            if (amountFloat != null && payer.isNotBlank()) {
                                viewModel.saveTransaction(
                                    amountFloat,
                                    payer,
                                    receiver.ifBlank { null },
                                    notes.ifBlank { null },
                                    onBack
                                )
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
            OutlinedTextField(value = amount, onValueChange = { amount = it }, label = { Text("Amount") })
            OutlinedTextField(value = payer, onValueChange = { payer = it }, label = { Text("Payer") })
            OutlinedTextField(value = receiver, onValueChange = { receiver = it }, label = { Text("Receiver") })
            OutlinedTextField(value = notes, onValueChange = { notes = it }, label = { Text("Notes") })
        }
    }
}