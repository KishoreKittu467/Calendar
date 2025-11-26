package com.kkapps.bubbles.features.budget

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import model.Transaction
import model.TxType

class BudgetViewModel : ViewModel() {
    var transactions by mutableStateOf(listOf<Transaction>())
        private set

    var searchQuery by mutableStateOf("")
    var sortOption by mutableStateOf("Date")
    var filterOption by mutableStateOf("All")

    val filteredSortedTransactions: List<Transaction>
        get() {
            var result = transactions
            if (searchQuery.isNotBlank()) {
                result = result.filter {
                    it.payer.toString().contains(searchQuery, true) ||
                    it.receiver?.toString()?.contains(searchQuery, true) == true ||
                    it.amount.toString().contains(searchQuery, true) ||
                    it.txType?.toString()?.contains(searchQuery, true) == true
                }
            }
            result = when (filterOption) {
                "Income" -> result.filter { it.txType == TxType.Credit }
                "Expense" -> result.filter { it.txType == TxType.Debt }
                else -> result
            }
            result = when (sortOption) {
                "Amount" -> result.sortedByDescending { it.amount }
                "Type" -> result.sortedBy { it.txType?.toString() ?: "" }
                else -> result // Date sorting would require a date field
            }
            return result
        }

    fun addTransaction(transaction: Transaction) {
        transactions = transactions + transaction
    }

    fun updateTransaction(updated: Transaction) {
        transactions = transactions.map { if (it == updated) updated else it }
    }

    fun getTransactionById(id: String): Transaction? {
        return transactions.firstOrNull()
    }
}