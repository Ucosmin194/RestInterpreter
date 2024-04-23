package model

import com.rest.interpreter.model.Request
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RequestTest {
    @Test
    fun testRequest() {
        val request = Request("1", "1", "GET", "http://localhost:8080", mutableMapOf("key" to "value"), "body")
        assertEquals("1", request.requestId)
        assertEquals("1", request.collectionId)
        assertEquals("GET", request.method)
        assertEquals("http://localhost:8080", request.url)
        assertEquals(mutableMapOf("key" to "value"), request.headers)
        assertEquals("body", request.body)
    }
}