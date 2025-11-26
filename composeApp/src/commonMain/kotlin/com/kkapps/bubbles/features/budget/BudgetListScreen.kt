package com.kkapps.bubbles.features.budget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kkapps.common.ui.components.icons.AddIcon
import model.Transaction
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun BudgetListScreen(
    viewModel: BudgetViewModel = koinViewModel(),
    onAddTransaction: () -> Unit,
    onTransactionClick: (transactionId: String) -> Unit
) {
    val searchQuery = viewModel.searchQuery
    val sortOption = viewModel.sortOption
    val filterOption = viewModel.filterOption
    val transactions = viewModel.filteredSortedTransactions

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            BasicTextField(
                value = searchQuery,
                onValueChange = { viewModel.searchQuery = it },
                modifier = Modifier.weight(1f).padding(end = 8.dp),
                decorationBox = { innerTextField ->
                    Box(Modifier.padding(8.dp)) {
                        if (searchQuery.isEmpty()) Text("Search...")
                        innerTextField()
                    }
                }
            )
            DropdownMenuBox(
                label = "Sort",
                options = listOf("Date", "Amount", "Type"),
                selected = sortOption,
                onOptionSelected = { viewModel.sortOption = it }
            )
            DropdownMenuBox(
                label = "Filter",
                options = listOf("All", "Income", "Expense"),
                selected = filterOption,
                onOptionSelected = { viewModel.filterOption = it }
            )
        }
        Spacer(Modifier.height(16.dp))
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(transactions.size) { idx ->
                val tx = transactions[idx]
                TransactionListItem(
                    transaction = tx,
                    onClick = { onTransactionClick(idx.toString()) }
                )
                HorizontalDivider()
            }
        }
        Box(Modifier.fillMaxWidth(), contentAlignment = androidx.compose.ui.Alignment.BottomEnd) {
            FloatingActionButton(onClick = onAddTransaction) {
                Icon(AddIcon, contentDescription = "Add Transaction")
            }
        }
    }
}

@Composable
fun DropdownMenuBox(label: String, options: List<String>, selected: String, onOptionSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Box {
        Button(onClick = { expanded = true }) {
            Text("$label: $selected")
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { option ->
                DropdownMenuItem(onClick = {
                    onOptionSelected(option)
                    expanded = false
                }, text = { Text(option) })
            }
        }
    }
}

@Composable
fun TransactionListItem(transaction: Transaction, onClick: () -> Unit) {
    ListItem(
        headlineContent = { Text("${transaction.payer} → ${transaction.receiver ?: "-"}") },
        supportingContent = { Text("${transaction.amount} | ${transaction.txType}") },
        modifier = Modifier.fillMaxWidth().clickable { onClick() }
    )
}