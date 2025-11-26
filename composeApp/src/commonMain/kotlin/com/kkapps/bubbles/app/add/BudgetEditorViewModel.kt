package com.kkapps.bubbles.app.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kkapps.bubbles.features.entries.domain.entities.Transaction
import com.kkapps.bubbles.features.entries.domain.repository.EntryRepository
import kotlinx.coroutines.launch
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class BudgetEditorViewModel(
    private val repository: EntryRepository,
) : ViewModel() {

    @OptIn(ExperimentalTime::class)
    fun saveTransaction(
        amount: Float,
        payer: String,
        receiver: String?,
        notes: String?,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            val now = Clock.System.now().toEpochMilliseconds()
            val transaction = Transaction(
                amount = amount,
                payerName = payer,
                receiverName = receiver,
                notes = notes,
                createdAt = now,
                lastModifiedAt = now
            )
            repository.saveTransaction(transaction)
            onSuccess()
        }
    }
}