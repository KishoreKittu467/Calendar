package com.kkapps.bubbles.app.data.repository

import com.kkapps.bubbles.app.domain.repository.BubblesRepository
import com.kkapps.bubbles.features.book.domain.entities.Bubble

class BubblesRepositoryImpl : BubblesRepository {
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