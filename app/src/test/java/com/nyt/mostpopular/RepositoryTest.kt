package com.nyt.mostpopular

import com.nyt.network.ApiRepository
import com.nyt.network.ApiWrapper
import junit.framework.Assert
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.io.File
import java.net.HttpURLConnection

/**
 * Created by dastaniqbal on 11/07/2019.
 * 11/07/2019 11:44
 */
@RunWith(JUnit4::class)
class RepositoryTest : MockServerTest() {
    private val TAG = this::class.java.simpleName

    private val repo = ApiRepository.INSTANCE

    fun mockHttpResponse(fileName: String, responseCode: Int) =
        mockServer.enqueue(
            MockResponse()
                .setResponseCode(responseCode)
                .setBody(getJson(fileName))
        )

    private fun getJson(path: String): String {
        val uri = javaClass.classLoader?.getResource(path)!!
        val file = File(uri.path)
        return String(file.readBytes())
    }

    @Test
    fun successResponse() {
        ApiWrapper.SERVICE_URL = getMockUrl()
        mockHttpResponse("1Day.json", HttpURLConnection.HTTP_OK)
        runBlocking {
            val result = repo.mostViewed(1) {
            }
            Assert.assertEquals(20, result.size)
            Assert.assertEquals("Article", result.first().type)
            Assert.assertEquals(
                "https://www.nytimes.com/2019/07/10/us/black-woman-american-airlines-cover-up.html",
                result.first().url
            )
            Assert.assertEquals("The New York Times", result.first().source)

            Assert.assertEquals(1, result.first().media.size)
            Assert.assertEquals("Tisha Rowe", result.first().media.first().copyright)

            Assert.assertEquals(3, result.first().media.first().mediaMetadata!!.size)
            Assert.assertEquals(
                "https://static01.nyt.com/images/2019/07/10/multimedia/10xp-airline/10xp-airline-thumbStandard.jpg",
                result.first().media.first().mediaMetadata!!.first().url
            )
        }
    }
}