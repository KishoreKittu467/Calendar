package com.kkapps.bubbles.di

import com.kkapps.bubbles.features.book.data.database.FavoriteBookDataSource
import com.kkapps.bubbles.features.book.data.network.BookDataSource
import com.kkapps.bubbles.features.book.data.database.LocalBookDataSource
import com.kkapps.bubbles.features.book.data.repository.BookRepositoryImpl
import com.kkapps.bubbles.features.book.domain.repository.BookRepository
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.js.Js
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformModules: Module
    get() = module {
        single<HttpClientEngine> { Js.create() }
    }

actual val domainModules: Module
    get() = module {
        singleOf(::BookRepositoryImpl).bind<BookRepository>()
        singleOf(::LocalBookDataSource).bind<BookDataSource>()
        single { FavoriteBookDataSource() }
    }