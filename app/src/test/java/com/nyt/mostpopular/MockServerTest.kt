package com.nyt.mostpopular

import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.mockito.MockitoAnnotations


/**
 * Created by dastaniqbal on 11/07/2019.
 * 11/07/2019 11:47
 */
open class MockServerTest {
    private val TAG = this::class.java.simpleName
    protected lateinit var mockServer: MockWebServer

    @Before
    open fun setUp() {
        MockitoAnnotations.initMocks(this)

        configureMockServer()
    }

    @After
    open fun tearDown() {
        stopMockServer()
    }

    private fun configureMockServer() {
        mockServer = MockWebServer()
        mockServer.start()
    }

    private fun stopMockServer() {
        mockServer.shutdown()
    }

    fun getMockUrl() = mockServer.url("/").toString()
}