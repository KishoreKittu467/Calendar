package com.kkapps.bubbles.app.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kkapps.bubbles.core.presentation.theme.ThemeMode
import com.kkapps.common.ui.components.icons.ArrowBackIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    currentTheme: ThemeMode,
    onThemeChange: (ThemeMode) -> Unit,
    userName: String,
    onUserNameChange: (String) -> Unit,
    profileEmoji: String,
    onProfileEmojiChange: (String) -> Unit,
    isDeveloperModeEnabled: Boolean = false,
    onDatabaseEditorClick: () -> Unit = {},
    onBack: () -> Unit
) {
    var localUserName by remember { mutableStateOf(userName) }
    var localEmoji by remember { mutableStateOf(profileEmoji) }
    var newCategory by remember { mutableStateOf("") }
    var newTag by remember { mutableStateOf("") }
    val categories = remember { mutableStateListOf("Work", "Home") }
    val tags = remember { mutableStateListOf("Urgent", "Idea") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        androidx.compose.material3.Icon(imageVector = ArrowBackIcon, contentDescription = "Back")
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
                Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text("Theme")
                ThemeMode.entries.forEach { mode ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = currentTheme == mode,
                            onClick = { onThemeChange(mode) }
                        )
                        Text(mode.name)
                    }
                }

                Text("Profile")
                OutlinedTextField(
                    value = localUserName,
                    onValueChange = { localUserName = it },
                    label = { Text("Username") }
                )
                OutlinedTextField(
                    value = localEmoji,
                    onValueChange = { localEmoji = it },
                    label = { Text("Profile Emoji (e.g., 🙂)") }
                )
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(onClick = { onUserNameChange(localUserName) }) { Text("Save Name") }
                    Button(onClick = { onProfileEmojiChange(localEmoji) }) { Text("Save Emoji") }
                }

                Text("Categories")
                Row(verticalAlignment = Alignment.CenterVertically) {
                    OutlinedTextField(
                        value = newCategory,
                        onValueChange = { newCategory = it },
                        label = { Text("Add category") },
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(Modifier.height(0.dp))
                    Button(onClick = {
                        if (newCategory.isNotBlank()) {
                            categories += newCategory.trim()
                            newCategory = ""
                        }
                    }) { Text("Add") }
                }
                Column {
                    categories.forEach { Text("• $it") }
                }

                Text("Tags")
                Row(verticalAlignment = Alignment.CenterVertically) {
                    OutlinedTextField(
                        value = newTag,
                        onValueChange = { newTag = it },
                        label = { Text("Add tag") },
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(Modifier.height(0.dp))
                    Button(onClick = {
                        if (newTag.isNotBlank()) {
                            tags += newTag.trim()
                            newTag = ""
                        }
                    }) { Text("Add") }
                }
                Column {
                    tags.forEach { Text("• $it") }
                }

                Spacer(Modifier.height(8.dp))
                Text("Changes are saved locally.", textAlign = TextAlign.Start)

                if (isDeveloperModeEnabled) {
                    Spacer(Modifier.height(16.dp))
                    HorizontalDivider()
                    Spacer(Modifier.height(16.dp))

                    Text("Developer Options", style = androidx.compose.material3.MaterialTheme.typography.titleMedium)
                    Button(onClick = onDatabaseEditorClick) {
                        Text("Database Editor")
                    }
                }
            }
        }
    }
}