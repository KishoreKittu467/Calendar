package com.kkapps.bubbles.app

import kotlinx.serialization.Serializable

sealed interface Route {

    // App flows
    @Serializable
    data object Splash : Route

    @Serializable
    data object Login : Route

    @Serializable
    data object HomeGraph : Route

    @Serializable
    data object Settings : Route

    // Add routes
    @Serializable
    data object AddEntry : Route

    @Serializable
    data object AddDiary : Route

    @Serializable
    data object AddNote : Route

    @Serializable
    data object AddEvent : Route

    @Serializable
    data object AddBudget : Route

    @Serializable
    data object AddMood : Route

    @Serializable
    data object BookList: Route

    @Serializable
    data class BookDetail(val id: String): Route
}