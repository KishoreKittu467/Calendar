package com.kkapps.bubbles.features.book.presentation

import Greeting
import androidx.lifecycle.ViewModel
import com.kkapps.bubbles.app.domain.repository.BubblesRepository

class BubblesViewModel(
    private val bubblesRepository: BubblesRepository,
    private val greeting: Greeting
): ViewModel() {

    fun platformName() = greeting.greet()
}