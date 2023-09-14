package com.beok.domain.repository

import com.beok.domain.BooksDataSource
import com.beok.domain.BooksRepository
import com.beok.domain.BooksService
import com.beok.domain.util.MockUtil
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class BooksRepositoryTest {

    private lateinit var repository: BooksDataSource
    private val service: BooksService = mock()

    @Before
    fun setup() {
        repository = BooksRepository(booksService = service)
        
    }

    @Test
    fun requestBookSearchTest() = runBlocking<Unit> {
        val mockData = MockUtil.mockToBooksResponse()
        whenever(service.search(keyword = "effective kotlin")).thenReturn(mockData)

        repository.searchBook(keyword = "effective kotlin").getOrNull()?.let {
            assertThat(it.totalItems).isEqualTo(mockData.totalItems)
            assertThat(it.kind).isEqualTo(mockData.kind)
        }
    }
}
