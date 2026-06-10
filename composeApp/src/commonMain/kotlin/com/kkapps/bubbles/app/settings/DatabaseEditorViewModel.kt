package com.kkapps.bubbles.app.settings

import androidx.lifecycle.ViewModel

expect class DatabaseEditorViewModel:  ViewModel {

    fun loadTableData(tableName: String, onDataLoaded: (rows: List<Map<String, String>>, columns: List<String>) -> Unit)

    fun saveTableData(tableName: String, rows: List<Map<String, String>>)
}