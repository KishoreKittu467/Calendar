package com.kkapps.bubbles.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.kkapps.bubbles.app.settings.DatabaseEditorViewModel
import com.kkapps.bubbles.features.book.data.database.DatabaseFactory
import com.kkapps.bubbles.features.book.data.database.FavoriteBookDatabase
import com.kkapps.bubbles.features.book.data.database.KtorBookDataSource
import com.kkapps.bubbles.features.book.data.sources.BookDataSource
import com.kkapps.bubbles.features.book.data.repository.BookRepositoryImpl
import com.kkapps.bubbles.features.book.domain.repository.BookRepository
import com.kkapps.bubbles.features.entries.data.repository.EntryRepositoryImpl
import com.kkapps.bubbles.features.entries.domain.repository.EntryRepository
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val domainModules: Module
    get() = module {
        singleOf(::KtorBookDataSource).bind<BookDataSource>()
        single {
            get<DatabaseFactory>().create()
                .setDriver(BundledSQLiteDriver())
                .build()
        }
        singleOf(::BookRepositoryImpl).bind<BookRepository>()
        singleOf(::EntryRepositoryImpl).bind<EntryRepository>()
        single { get<FavoriteBookDatabase>().favoriteBookDao }
        viewModelOf(::DatabaseEditorViewModel)
    }