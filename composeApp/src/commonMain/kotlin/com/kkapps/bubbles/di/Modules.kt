package com.kkapps.bubbles.di

import Greeting
import com.kkapps.bubbles.core.data.HttpClientFactory
import com.kkapps.bubbles.features.book.data.repository.BubblesRepositoryImpl
import com.kkapps.bubbles.features.book.domain.repository.BubblesRepository
import com.kkapps.bubbles.features.book.presentation.BubblesViewModel
import com.kkapps.bubbles.features.book.presentation.SelectedBookViewModel
import com.kkapps.bubbles.features.book.presentation.book_detail.BookDetailViewModel
import com.kkapps.bubbles.features.book.presentation.book_list.BookListViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModules: Module

expect val domainModules: Module

val sharedModules: Module
    get () = module {
        singleOf(::BubblesRepositoryImpl).bind<BubblesRepository>()
        factory { Greeting() }
        single { HttpClientFactory.create(get()) }
        viewModelOf(::BubblesViewModel)
        viewModelOf(::BookListViewModel)
        viewModelOf(::BookDetailViewModel)
        viewModelOf(::SelectedBookViewModel)
    }