package com.kkapps.bubbles.app

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kkapps.bubbles.app.add.AddEntryScreen
import com.kkapps.bubbles.app.add.BudgetEditorScreen
import com.kkapps.bubbles.app.add.EventEditorScreen
import com.kkapps.bubbles.app.add.MoodEditorScreen
import com.kkapps.bubbles.app.add.NoteEditorScreen
import com.kkapps.bubbles.app.auth.LoginScreen
import com.kkapps.bubbles.app.home.HomeScreenRoot
import com.kkapps.bubbles.app.settings.SettingsScreen
import com.kkapps.bubbles.app.splash.SplashScreen
import com.kkapps.bubbles.core.presentation.extensions.sharedKoinViewModel
import com.kkapps.bubbles.core.presentation.theme.ThemeMode
import com.kkapps.bubbles.features.book.presentation.SelectedBookViewModel
import com.kkapps.bubbles.features.book.presentation.book_detail.BookDetailAction
import com.kkapps.bubbles.features.book.presentation.book_detail.BookDetailScreenRoot
import com.kkapps.bubbles.features.book.presentation.book_detail.BookDetailViewModel
import com.kkapps.bubbles.features.book.presentation.book_list.BookListScreenRoot
import com.kkapps.bubbles.features.book.presentation.book_list.BookListViewModel
import com.kkapps.bubbles.features.budget.BudgetEntryScreen
import com.kkapps.bubbles.features.budget.BudgetListScreen
import com.kkapps.bubbles.features.budget.EntryMode
import com.kkapps.bubbles.features.entries.presentation.diary.DiaryEditorScreen
import kotlinx.coroutines.delay
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()

        // Simple in-memory login flag for demo; replace with real auth state as needed.
        var isLoggedIn by rememberSaveable { mutableStateOf(false) }

        // Simple in-memory settings state for navigation demo
        var themeMode by rememberSaveable { mutableStateOf(ThemeMode.System) }
        var userName by rememberSaveable { mutableStateOf("User") }
        var profileEmoji by rememberSaveable { mutableStateOf("🙂") }

        NavHost(
            navController = navController,
            startDestination = Route.HomeGraph
        ) {
            composable<Route.Splash>(
                enterTransition = { fadeIn(animationSpec = tween(300)) + scaleIn(initialScale = 0.9f) },
                exitTransition = { fadeOut(animationSpec = tween(300)) + scaleOut(targetScale = 1.05f) }
            ) {
                SplashScreen()
                LaunchedEffect(Unit) {
                    delay(1200)
                    if (isLoggedIn) {
                        navController.navigate(Route.HomeGraph) {
                            popUpTo<Route.Splash> { inclusive = true }
                        }
                    } else {
                        navController.navigate(Route.Login) {
                            popUpTo<Route.Splash> { inclusive = true }
                        }
                    }
                }
            }

            composable<Route.Login>(
                enterTransition = {
                    fadeIn(animationSpec = tween(250)) + scaleIn(initialScale = 0.95f)
                },
                exitTransition = {
                    fadeOut(animationSpec = tween(200)) + scaleOut(targetScale = 1.05f)
                }
            ) {
                LoginScreen(
                    onLogin = { _, _ ->
                        // Perform validation/auth here
                        isLoggedIn = true
                        navController.navigate(Route.HomeGraph) {
                            popUpTo<Route.Login> { inclusive = true }
                        }
                    }
                )
            }

            composable<Route.HomeGraph>(
                enterTransition = {
                    fadeIn(animationSpec = tween(250)) + scaleIn(initialScale = 0.95f)
                },
                exitTransition = {
                    fadeOut(animationSpec = tween(200)) + scaleOut(targetScale = 1.05f)
                }
            ) {
                HomeScreenRoot(
                    onAddDiary = { navController.navigate(Route.AddDiary) },
                    onAddNote = { navController.navigate(Route.AddNote) },
                    onAddEvent = { navController.navigate(Route.AddEvent) },
                    onAddBudget = { navController.navigate(Route.AddBudget) },
                    onAddMood = { navController.navigate(Route.AddMood) },
                    onAddEntry = { navController.navigate(Route.AddEntry) },
                    openBookList = { navController.navigate(Route.BookList) },
                    onProfileClick = {
                        navController.navigate(Route.Settings)
                    },
                    onSearchClick = {
                        // Search can be implemented in HomeScreen; no navigation needed here.
                    }
                )
            }

            composable<Route.AddEntry>(
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Up,
                        animationSpec = tween(250)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Down,
                        animationSpec = tween(250)
                    )
                }
            ) {
                AddEntryScreen(
                    onBack = { navController.navigateUp() },
                    onSave = { navController.navigateUp() }
                )
            }

            composable<Route.Settings>(
                enterTransition = {
                    fadeIn(animationSpec = tween(250)) + scaleIn(initialScale = 0.95f)
                },
                exitTransition = {
                    fadeOut(animationSpec = tween(200)) + scaleOut(targetScale = 1.05f)
                }
            ) {
                SettingsScreen(
                    currentTheme = themeMode,
                    onThemeChange = { themeMode = it },
                    userName = userName,
                    onUserNameChange = { userName = it },
                    profileEmoji = profileEmoji,
                    onProfileEmojiChange = { profileEmoji = it },
                    onBack = { navController.navigateUp() }
                )
            }

            composable<Route.AddBudget>(
                enterTransition = {
                    slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Up, tween(250))
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Down,
                        tween(250)
                    )
                }
            ) {
                BudgetEditorScreen(onBack = { navController.navigateUp() })
            }

            composable<Route.AddDiary>(
                enterTransition = {
                    slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Up, tween(250))
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Down,
                        tween(250)
                    )
                }
            ) {
                DiaryEditorScreen(onBack = { navController.navigateUp() })
            }

            composable<Route.AddEvent>(
                enterTransition = {
                    slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Up, tween(250))
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Down,
                        tween(250)
                    )
                }
            ) {
                EventEditorScreen(onBack = { navController.navigateUp() })
            }

            composable<Route.AddMood>(
                enterTransition = {
                    slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Up, tween(250))
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Down,
                        tween(250)
                    )
                }
            ) {
                MoodEditorScreen(onBack = { navController.navigateUp() })
            }

            composable<Route.AddNote>(
                enterTransition = {
                    slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Up, tween(250))
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Down,
                        tween(250)
                    )
                }
            ) {
                NoteEditorScreen(onBack = { navController.navigateUp() })
            }

            composable<Route.BookList>(
                exitTransition = { slideOutHorizontally() },
                popEnterTransition = { slideInHorizontally() }
            ) {
                val viewModel = koinViewModel<BookListViewModel>()
                val selectedBookViewModel =
                    it.sharedKoinViewModel<SelectedBookViewModel>(navController)

                LaunchedEffect(true) {
                    selectedBookViewModel.onSelectBook(null)
                }

                BookListScreenRoot(
                    viewModel = viewModel,
                    onBookClick = { book ->
                        selectedBookViewModel.onSelectBook(book)
                        navController.navigate(
                            Route.BookDetail(book.id)
                        )
                    }
                )
            }
            composable<Route.BookDetail>(
                enterTransition = {
                    slideInHorizontally { initialOffset ->
                        initialOffset
                    }
                },
                exitTransition = {
                    slideOutHorizontally { initialOffset ->
                        initialOffset
                    }
                }
            ) { navBackStackEntry ->
                val selectedBookViewModel =
                    navBackStackEntry.sharedKoinViewModel<SelectedBookViewModel>(navController)
                val viewModel = koinViewModel<BookDetailViewModel>()
                val selectedBook by selectedBookViewModel.selectedBook.collectAsStateWithLifecycle()

                LaunchedEffect(selectedBook) {
                    selectedBook?.let {
                        viewModel.onAction(BookDetailAction.OnSelectedBookChange(it))
                    }
                }

                BookDetailScreenRoot(
                    viewModel = viewModel,
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }

            composable("budgetList") {
                BudgetListScreen(
                    viewModel = koinViewModel(),
                    onAddTransaction = { navController.navigate("budgetEntry?mode=CREATE") },
                    onTransactionClick = { id -> navController.navigate("budgetEntry?mode=VIEW&id=$id") }
                )
            }
            composable(
                route = "budgetEntry?mode={mode}&id={id}",
                arguments = listOf(
                    navArgument("mode") { type = NavType.StringType; defaultValue = "CREATE" },
                    navArgument("id") { type = NavType.StringType; nullable = true }
                )
            ) { backStackEntry ->
//            val modeStr = backStackEntry.arguments?.toString()?.get("mode") as? String ?: "CREATE"
//            val id = backStackEntry.arguments?.toString()?.get("id") as? String
//            val mode = when (modeStr) {
//                "VIEW" -> EntryMode.VIEW
//                "EDIT" -> EntryMode.EDIT
//                else -> EntryMode.CREATE
//            }
                BudgetEntryScreen(
                    mode = EntryMode.CREATE,
                    onSave = {
                        navController.popBackStack()
                    },
                    onEdit = {
//                    navController.navigate("budgetEntry?mode=EDIT&id=$id")
                    }
                )
            }
            composable(
                route = "budgetEntry?mode={mode}",
                arguments = listOf(navArgument("mode") {
                    type = NavType.StringType; defaultValue = "CREATE"
                })
            ) { backStackEntry ->
                BudgetEntryScreen(
                    mode = EntryMode.CREATE,
                    transaction = null,
                    onSave = {
                        navController.popBackStack()
                    },
                    onEdit = {}
                )
            }
        }
    }
}