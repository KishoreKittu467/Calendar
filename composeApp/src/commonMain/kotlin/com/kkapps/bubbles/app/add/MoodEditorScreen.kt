package com.kkapps.bubbles.app.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import metaModel.HabitType
import model.BettermentType
import model.Entity
import model.Image
import model.Milestone
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoodEditorScreen(onBack: () -> Unit) {
    val viewModel = koinViewModel<MoodEditorViewModel>()
    val options = listOf(
        Milestone(bettermentType = BettermentType.CloserIsBetter, iEntity = Entity(name = "SAD", notes = "Sad", image = Image(emoji = "😢"), rank = 1f)),
        Milestone(bettermentType = BettermentType.CloserIsBetter, iEntity = Entity(name = "OKAY", notes = "Okay", image = Image(emoji = "😐"), rank = 2f)),
        Milestone(bettermentType = BettermentType.CloserIsBetter, iEntity = Entity(name = "HAPPY", notes = "Happy", image = Image(emoji = "😊"), rank = 3f)),
        Milestone(bettermentType = BettermentType.CloserIsBetter, iEntity = Entity(name = "JOY", notes = "Joyful", image = Image(emoji = "😁"), rank = 4f))
    )
    var selectedMood by remember { mutableStateOf(options.first()) }
    val habitType = HabitType.MilestoneOnly

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Add Mood") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = ArrowBackIcon, contentDescription = "Back")
                    }
                },
                actions = {
                    TextButton(
                        onClick = {
                            viewModel.saveMood(
                                selectedMood.name,
                                selectedMood.image?.emoji ?: "",
                                selectedMood.rank ?: 0f,
                                selectedMood.notes,
                                onBack
                            )
                        }
                    ) {
                        Text("Save")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Mood type: $habitType")
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                options.forEach { m ->
                    ElevatedAssistChip(
                        onClick = { selectedMood = m },
                        label = { Text(m.image?.emoji ?: m.name) }
                    )
                }
            }
            Text("Selected: ${selectedMood.image?.emoji} ${selectedMood.name}")
        }
    }
}