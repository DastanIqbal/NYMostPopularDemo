package com.nyt

import androidx.test.platform.app.InstrumentationRegistry
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.mockito.MockitoAnnotations
import java.io.InputStream


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


    fun mockHttpResponse(fileName: String, responseCode: Int) {
        val json = getJson(fileName)
        return mockServer.enqueue(
            MockResponse()
                .setResponseCode(responseCode)
                .setBody(json))
    }

    fun getJson(path : String) : String {
        val inputStream: InputStream = InstrumentationRegistry.getInstrumentation().context.resources.assets.open(path)
        return inputStream.bufferedReader().use{it.readText()}
    }
}