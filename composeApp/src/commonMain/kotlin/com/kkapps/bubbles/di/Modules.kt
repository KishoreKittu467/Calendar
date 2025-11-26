package com.kkapps.bubbles.di

import Greeting
import com.kkapps.bubbles.app.add.BudgetEditorViewModel
import com.kkapps.bubbles.app.add.EventEditorViewModel
import com.kkapps.bubbles.app.add.MoodEditorViewModel
import com.kkapps.bubbles.app.add.NoteEditorViewModel
import com.kkapps.bubbles.app.data.repository.BubblesRepositoryImpl
import com.kkapps.bubbles.app.domain.repository.BubblesRepository
import com.kkapps.bubbles.app.home.tabs.BoardTabViewModel
import com.kkapps.bubbles.core.data.HttpClientFactory
import com.kkapps.bubbles.features.book.presentation.BubblesViewModel
import com.kkapps.bubbles.features.book.presentation.SelectedBookViewModel
import com.kkapps.bubbles.features.book.presentation.book_detail.BookDetailViewModel
import com.kkapps.bubbles.features.book.presentation.book_list.BookListViewModel
import com.kkapps.bubbles.features.budget.BudgetViewModel
import com.kkapps.bubbles.features.entries.presentation.diary.DiaryEditorViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModules: Module

expect val domainModules: Module
val sharedModules: Module
    get() = module {
        single { HttpClientFactory.create(get()) }
    }

val featureModules: Module
    get() = module {
        singleOf(::BubblesRepositoryImpl).bind<BubblesRepository>()

        factory { Greeting() }

        viewModelOf(::BubblesViewModel)
        viewModelOf(::BookListViewModel)
        viewModelOf(::BookDetailViewModel)
        viewModelOf(::SelectedBookViewModel)
        viewModelOf(::DiaryEditorViewModel)
        viewModelOf(::MoodEditorViewModel)
        viewModelOf(::EventEditorViewModel)
        viewModelOf(::NoteEditorViewModel)
        viewModelOf(::BudgetEditorViewModel)
        viewModelOf(::BudgetViewModel)
        viewModelOf(::BoardTabViewModel)
    }