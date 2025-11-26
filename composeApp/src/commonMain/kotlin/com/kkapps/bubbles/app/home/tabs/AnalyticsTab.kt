package com.kkapps.bubbles.app.home.tabs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kkapps.bubbles.app.data.repository.DemoRepository
import com.kkapps.bubbles.app.presentation.Bar

@Composable
fun AnalyticsTab() {
    val stats = DemoRepository.analytics()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Mood streak", style = MaterialTheme.typography.titleMedium)
        Bar(value = stats.moodStreak, max = 30)
        Text("Budget this month", style = MaterialTheme.typography.titleMedium)
        Bar(value = stats.budgetUsage.toInt(), max = 100)
        Text("Entries by type", style = MaterialTheme.typography.titleMedium)
        stats.entriesByType.forEach { (type, count) ->
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(type)
                Bar(value = count, max = 20, height = 10)
            }
        }
    }
}