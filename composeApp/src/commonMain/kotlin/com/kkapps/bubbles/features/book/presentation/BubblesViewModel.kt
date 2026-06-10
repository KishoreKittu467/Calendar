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

// FlexBox
//  - Is scroll disabled for Flex so that elements can wrap to next line ?

// Grid

//  - Bug on Item's columnSpan > Grid's column count & padding not applied to the spanned item
//  - Question on why 1000 limit for row / column count ?

// mediaQuery

//  - Feedback on notch: It can also be in mediaQuery
//  - Feedback on viewDistance: it for XR, should be on derivedMediaQuery also