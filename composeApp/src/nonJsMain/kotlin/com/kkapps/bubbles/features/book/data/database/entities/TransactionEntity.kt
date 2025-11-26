package com.kkapps.bubbles.features.book.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
actual data class TransactionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val amount: Float,
    val payerName: String,
    val receiverName: String? = null,
    val txType: String? = null, // Paid, Took, Debt, Credit
    val txMode: String? = null, // UPI, Cash, Card, etc.
    val txStatus: String? = null, // Asset, Liability, Income, Expense, etc.
    val createdAt: Long,
    val lastModifiedAt: Long,
    val notes: String? = null,
    val tags: String? = null,
    val categories: String? = null,
    val isMarked: Boolean = false
)