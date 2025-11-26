package com.kkapps.bubbles.features.budget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kkapps.common.ui.components.icons.EditIcon
import model.Entity
import model.Event
import model.Transaction
import model.TxType
import org.koin.compose.viewmodel.koinViewModel
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

enum class EntryMode { CREATE, VIEW, EDIT }

@OptIn(ExperimentalTime::class)
@Composable
fun BudgetEntryScreen(
    mode: EntryMode,
    transaction: Transaction? = null,
    onSave: () -> Unit,
    onEdit: () -> Unit,
    viewModel: BudgetViewModel = koinViewModel()
) {
    var payer by remember { mutableStateOf(transaction?.payer) }
    var receiver by remember { mutableStateOf(transaction?.receiver) }
    var txType by remember { mutableStateOf(transaction?.txType) }
    var amount by remember { mutableStateOf(transaction?.amount?.toString() ?: "") }
    var dateTime by remember { mutableStateOf(transaction?.startTime ?: Clock.System.now()) }
    var description by remember { mutableStateOf(transaction?.notes ?: "") }
    var isEditing by remember { mutableStateOf(mode == EntryMode.CREATE || mode == EntryMode.EDIT) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = if (mode == EntryMode.CREATE) "New Transaction" else "Transaction Details",
                style = MaterialTheme.typography.titleLarge
            )
            if (mode == EntryMode.VIEW && !isEditing) {
                IconButton(onClick = { isEditing = true; onEdit() }) {
                    Icon(EditIcon, contentDescription = "Edit")
                }
            }
        }
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = amount,
            onValueChange = { if (isEditing) amount = it },
            label = { Text("Amount") },
            enabled = isEditing,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        // TODO: Replace with actual person pickers
        OutlinedTextField(
            value = payer?.toString() ?: "",
            onValueChange = {},
            label = { Text("Payer") },
            enabled = false,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = receiver?.toString() ?: "",
            onValueChange = {},
            label = { Text("Receiver") },
            enabled = false,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        // Transaction Type Dropdown
        var txTypeExpanded by remember { mutableStateOf(false) }
        Box {
            OutlinedButton(
                onClick = { if (isEditing) txTypeExpanded = true },
                enabled = isEditing
            ) {
                Text("Type: ${txType?.toString() ?: "Select"}")
            }
            DropdownMenu(expanded = txTypeExpanded, onDismissRequest = { txTypeExpanded = false }) {
                TxType.allTxTypes.forEach { subclass ->
                    DropdownMenuItem(onClick = {
                        txType = subclass
                        txTypeExpanded = false
                    }, text = { Text(subclass.toString()) })
                }
            }
        }
        Spacer(Modifier.height(8.dp))
        // Date/Time (placeholder)
        OutlinedTextField(
            value = dateTime.toString(),
            onValueChange = {},
            label = { Text("Date & Time") },
            enabled = false,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = description,
            onValueChange = { if (isEditing) description = it },
            label = { Text("Description") },
            enabled = isEditing,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))
        if (isEditing) {
            Button(
                onClick = {
                    val amt = amount.toFloatOrNull() ?: 0f
                    if (payer != null && amt > 0) {
                        viewModel.addTransaction(Transaction(
                            amount = amt,
                            payer = payer!!,
                            txType = txType,
                            txMode = null,
                            receiver = receiver,
                            txStatus = null,
                            iEvent = Event(
                                id = "trx_${Clock.System.now().toEpochMilliseconds()}",
                                createdAt = Clock.System.now().toEpochMilliseconds(),
                                startTime = Clock.System.now().toEpochMilliseconds(),
                                duration = 0L,
                                iEntity = Entity(name = "Tx")
                            ),
                        ))
                        isEditing = false
                        onSave()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save")
            }
        }
    }
}