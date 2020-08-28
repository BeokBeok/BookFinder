package com.beok.domain.service

import com.beok.domain.BooksService
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class BooksServiceTest : BaseService<BooksService>() {

    private lateinit var service: BooksService

    @Before
    fun initService() {
        service = createService(BooksService::class.java)
    }

    @Test
    fun searchBookTest() = runBlocking<Unit> {
        enqueueResponse("search_book.json")
        val response = service.search("effective kotlin")
        mockWebServer.takeRequest()

        assertThat(response.items.size).isEqualTo(10)
        assertThat(response.totalItems).isEqualTo(229)
        assertThat(response.kind).isEqualTo("books#volumes")
    }
}
