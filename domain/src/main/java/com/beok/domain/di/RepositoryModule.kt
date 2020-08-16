package com.beok.domain.di

import com.beok.domain.BooksDataSource
import com.beok.domain.BooksRepository
import com.beok.domain.BooksService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityRetainedComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideBooksRepository(booksService: BooksService): BooksDataSource =
        BooksRepository(booksService)
}
