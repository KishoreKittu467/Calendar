package com.kkapps.bubbles.app.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import com.kkapps.bubbles.app.home.tabs.AnalyticsTab
import com.kkapps.bubbles.app.home.tabs.BoardTab
import com.kkapps.bubbles.app.home.tabs.CalendarTab
import com.kkapps.bubbles.app.home.tabs.DashboardTab
import com.kkapps.common.ui.components.icons.AddIcon
import com.kkapps.common.ui.components.icons.SearchIcon
import org.jetbrains.compose.ui.tooling.preview.Preview

private enum class HomeTab(val title: String, val emoji: String) {
    Dashboard("Dashboard", "🏠"),
    Calendar("Calendar", "📅"),
    Boards("Boards", "🧩"),
    Analytics("Analytics", "📊");
}

@Composable
fun HomeScreenRoot(
    onAddDiary: () -> Unit,
    onAddNote: () -> Unit,
    onAddEvent: () -> Unit,
    onAddBudget: () -> Unit,
    onAddMood: () -> Unit,
    onAddEntry: () -> Unit,
    openBookList: () -> Unit,
    onProfileClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    BoxWithConstraints(Modifier.fillMaxSize()) {
        val isWide = maxWidth >= 840.dp
        var selectedTab by rememberSaveable { mutableStateOf(HomeTab.Dashboard) }

        if (isWide) {
            Row(Modifier.fillMaxSize()) {
                NavigationRail(
                    header = {
                        Text(
                            text = "🫧",
                            modifier = Modifier.padding(vertical = 16.dp),
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                ) {
                    HomeTab.entries.forEach { tab ->
                        NavigationRailItem(
                            selected = tab == selectedTab,
                            onClick = { selectedTab = tab },
                            icon = { Text(tab.emoji) },
                            label = { Text(tab.title) }
                        )
                    }
                }
                ContentScaffold(
                    selectedTab = selectedTab,
                    onAddDiary = onAddDiary,
                    onAddNote = onAddNote,
                    onAddEvent = onAddEvent,
                    onAddBudget = onAddBudget,
                    onAddMood = onAddMood,
                    onAddEntry = onAddEntry,
                    openBookList = openBookList,
                    onProfileClick = onProfileClick,
                    onSearchClick = onSearchClick,
                    modifier = Modifier.weight(1f)
                )
            }
        } else {
            ContentScaffold(
                selectedTab = selectedTab,
                onAddDiary = onAddDiary,
                onAddNote = onAddNote,
                onAddEvent = onAddEvent,
                onAddBudget = onAddBudget,
                onAddMood = onAddMood,
                onAddEntry = onAddEntry,
                openBookList = openBookList,
                onProfileClick = onProfileClick,
                onSearchClick = onSearchClick,
                bottomBar = {
                    NavigationBar {
                        HomeTab.entries.forEach { tab ->
                            NavigationBarItem(
                                selected = tab == selectedTab,
                                onClick = { selectedTab = tab },
                                icon = { Text(tab.emoji) },
                                label = { Text(tab.title) }
                            )
                        }
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ContentScaffold(
    selectedTab: HomeTab,
    onAddDiary: () -> Unit,
    onAddNote: () -> Unit,
    onAddEvent: () -> Unit,
    onAddBudget: () -> Unit,
    onAddMood: () -> Unit,
    onAddEntry: () -> Unit,
    openBookList: () -> Unit,
    onProfileClick: () -> Unit,
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier,
    bottomBar: (@Composable () -> Unit)? = null
) {
    var isFabExpanded by rememberSaveable { mutableStateOf(false) }
    val rotation by animateFloatAsState(if (isFabExpanded) 45f else 0f)

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(onClick = onProfileClick) {
                        Text("🙂")
                    }
                },
                title = {
                    Text(text = "🫧", style = MaterialTheme.typography.titleLarge)
                },
                actions = {
                    IconButton(onClick = onSearchClick) {
                        Icon(imageVector = SearchIcon, contentDescription = "Search")
                    }
                }
            )
        },
        floatingActionButton = {
            Box {
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(bottom = 80.dp),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    AnimatedVisibility(
                        visible = isFabExpanded,
                        enter = fadeIn() + expandVertically(),
                        exit = fadeOut() + shrinkVertically()
                    ) {
                        Column(
                            horizontalAlignment = Alignment.End,
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            FabMenuItem(
                                text = "Diary",
                                icon = "📔",
                                onClick = {
                                    isFabExpanded = false
                                    onAddDiary()
                                }
                            )
                            FabMenuItem(
                                text = "Note",
                                icon = "📝",
                                onClick = {
                                    isFabExpanded = false
                                    onAddNote()
                                }
                            )
                            FabMenuItem(
                                text = "Event",
                                icon = "📅",
                                onClick = {
                                    isFabExpanded = false
                                    onAddEvent()
                                }
                            )
                            FabMenuItem(
                                text = "Budget",
                                icon = "💰",
                                onClick = {
                                    isFabExpanded = false
                                    onAddBudget()
                                }
                            )
                            FabMenuItem(
                                text = "Mood",
                                icon = "😊",
                                onClick = {
                                    isFabExpanded = false
                                    onAddMood()
                                }
                            )
                        }
                    }
                }

                FloatingActionButton(
                    onClick = { isFabExpanded = !isFabExpanded },
                    modifier = Modifier.align(Alignment.BottomEnd)
                ) {
                    Icon(
                        imageVector = AddIcon,
                        contentDescription = if (isFabExpanded) "Close" else "Add Entry",
                        modifier = Modifier.rotate(rotation)
                    )
                }
            }
        },
        bottomBar = { bottomBar?.invoke() }
    ) { padding ->
        Surface(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            when (selectedTab) {
                HomeTab.Dashboard -> DashboardTab(userName = "User", openBookList = openBookList, onAddEntry = onAddEntry)
//                    DashboardScreen( userName = "User", onAddMood = onAddMood, onAddDiary = onAddDiary )
                HomeTab.Calendar -> CalendarTab() // CalendarScreen()
                HomeTab.Boards -> BoardTab() // BoardsScreen()
                HomeTab.Analytics -> AnalyticsTab() // AnalyticsScreen()
            }
        }
    }
}

@Composable
private fun FabMenuItem(
    text: String,
    icon: String,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Surface(
            color = MaterialTheme.colorScheme.surfaceVariant,
            shape = MaterialTheme.shapes.small
        ) {
            Text(
                text = text,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                style = MaterialTheme.typography.labelLarge
            )
        }
        SmallFloatingActionButton(
            onClick = onClick,
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ) {
            Text(icon)
        }
    }
}

@Preview
@Composable
private fun HomePreview() {
    HomeScreenRoot(
        onAddDiary = {},
        onAddNote = {},
        onAddEvent = {},
        onAddBudget = {},
        onAddMood = {},
        onAddEntry = {},
        openBookList = {},
        onProfileClick = {},
        onSearchClick = {}
    )
}