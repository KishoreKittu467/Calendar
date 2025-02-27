package com.kkapps.bubbles.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.kkapps.bubbles.features.book.data.database.DatabaseFactory
import com.kkapps.bubbles.features.book.data.database.FavoriteBookDatabase
import com.kkapps.bubbles.features.book.data.database.KtorBookDataSource
import com.kkapps.bubbles.features.book.data.network.BookDataSource
import com.kkapps.bubbles.features.book.data.repository.BookRepositoryImpl
import com.kkapps.bubbles.features.book.domain.repository.BookRepository
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
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
        single { get<FavoriteBookDatabase>().favoriteBookDao }
    }