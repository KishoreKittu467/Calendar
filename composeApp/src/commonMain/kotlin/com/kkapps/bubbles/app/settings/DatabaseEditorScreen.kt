package com.kkapps.bubbles.app.settings

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kkapps.common.ui.components.icons.ArrowBackIcon
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatabaseEditorScreen(
    onBack: () -> Unit
) {
    val viewModel = koinViewModel<DatabaseEditorViewModel>()
    var selectedTable by remember { mutableStateOf<String?>(null) }
    var showTableDropdown by remember { mutableStateOf(false) }
    var rows by remember { mutableStateOf<List<Map<String, String>>>(emptyList()) }
    var columns by remember { mutableStateOf<List<String>>(emptyList()) }

    val tables = listOf("BookEntity", "diary", "notes", "events", "tasks", "transactions", "habits", "moods")

    LaunchedEffect(selectedTable) {
        selectedTable?.let { table ->
            viewModel.loadTableData(table) { data, cols ->
                rows = data
                columns = cols
            }
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Database Editor") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = ArrowBackIcon, contentDescription = "Back")
                    }
                },
                actions = {
                    if (selectedTable != null) {
                        TextButton(
                            onClick = {
                                selectedTable?.let { table ->
                                    viewModel.saveTableData(table, rows)
                                }
                            }
                        ) {
                            Text("Save")
                        }
                    }
                }
            )
        }
    ) { padding ->
        Surface(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Table selector
                OutlinedButton(
                    onClick = { showTableDropdown = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(selectedTable ?: "Select Table")
                }

                DropdownMenu(
                    expanded = showTableDropdown,
                    onDismissRequest = { showTableDropdown = false }
                ) {
                    tables.forEach { table ->
                        DropdownMenuItem(
                            text = { Text(table) },
                            onClick = {
                                selectedTable = table
                                showTableDropdown = false
                            }
                        )
                    }
                }

                if (selectedTable != null && columns.isNotEmpty()) {
                    Text("Table: $selectedTable (${rows.size} rows)", style = MaterialTheme.typography.titleMedium)

                    // Table view
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Header row
                        item {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .horizontalScroll(rememberScrollState()),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                columns.forEach { column ->
                                    Surface(
                                        modifier = Modifier.width(150.dp),
                                        color = MaterialTheme.colorScheme.primaryContainer
                                    ) {
                                        Text(
                                            text = column,
                                            modifier = Modifier.padding(8.dp),
                                            style = MaterialTheme.typography.labelMedium
                                        )
                                    }
                                }
                            }
                        }

                        // Data rows
                        itemsIndexed(rows) { index, row ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .horizontalScroll(rememberScrollState()),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                columns.forEach { column ->
                                    OutlinedTextField(
                                        value = row[column] ?: "",
                                        onValueChange = { newValue ->
                                            val updatedRows = rows.toMutableList()
                                            val updatedRow = row.toMutableMap()
                                            updatedRow[column] = newValue
                                            updatedRows[index] = updatedRow
                                            rows = updatedRows
                                        },
                                        modifier = Modifier.width(150.dp),
                                        singleLine = true,
                                        textStyle = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }
                        }
                    }
                } else if (selectedTable != null) {
                    Text("No data available for this table", modifier = Modifier.align(Alignment.CenterHorizontally))
                }
            }
        }
    }
}