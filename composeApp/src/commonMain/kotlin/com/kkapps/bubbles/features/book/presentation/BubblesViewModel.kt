package com.kkapps.bubbles.features.book.presentation

import Greeting
import Platform
import androidx.lifecycle.ViewModel
import com.kkapps.bubbles.features.book.domain.repository.BubblesRepository

class BubblesViewModel(
    private val bubblesRepository: BubblesRepository,
    private val greeting: Greeting
): ViewModel() {

    fun platformName() = greeting.greet()
}