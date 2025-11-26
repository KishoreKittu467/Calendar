package com.kkapps.bubbles.app.domain.repository

import com.kkapps.bubbles.features.book.domain.entities.Bubble

interface BubblesRepository {
    fun getBubbles(): List<Bubble>
}