package com.kkapps.bubbles.features.book.data.repository

import com.kkapps.bubbles.features.book.domain.entities.Bubble
import com.kkapps.bubbles.features.book.domain.repository.BubblesRepository

class BubblesRepositoryImpl: BubblesRepository {
    override fun getBubbles(): List<Bubble> {
        return listOf(
            Bubble(
                color = "#ffffff",
                size = 1
            ),
            Bubble(
                color = "#fffffe",
                size = 2
            ),
            Bubble(
                color = "#fffffd",
                size = 3
            ),
            Bubble(
                color = "#fffffc",
                size = 4
            )
        )
    }
}