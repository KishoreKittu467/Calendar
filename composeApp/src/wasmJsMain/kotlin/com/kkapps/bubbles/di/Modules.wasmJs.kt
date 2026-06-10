package com.kkapps.bubbles.di

import com.kkapps.bubbles.app.settings.DatabaseEditorViewModel
import com.kkapps.bubbles.features.book.data.database.EntryDataSource
import com.kkapps.bubbles.features.book.data.database.FavoriteBookDao
import com.kkapps.bubbles.features.book.data.database.FavoriteBookDataSource
import com.kkapps.bubbles.features.book.data.database.SampleBookDataSource
import com.kkapps.bubbles.features.book.data.repository.BookRepositoryImpl
import com.kkapps.bubbles.features.book.data.sources.BookDataSource
import com.kkapps.bubbles.features.book.domain.repository.BookRepository
import com.kkapps.bubbles.features.entries.data.repository.EntryRepositoryImpl
import com.kkapps.bubbles.features.entries.domain.repository.EntryRepository
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.js.Js
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformModules: Module
    get() = module {
        single<HttpClientEngine> { Js.create() }
    }

actual val domainModules: Module
    get() = module {
        singleOf(::BookRepositoryImpl).bind<BookRepository>()
        singleOf(::EntryRepositoryImpl).bind<EntryRepository>()
        singleOf(::SampleBookDataSource).bind<BookDataSource>()
        singleOf(::FavoriteBookDataSource).bind<FavoriteBookDao>()
        single { EntryDataSource() }
        viewModelOf(::DatabaseEditorViewModel)
    }