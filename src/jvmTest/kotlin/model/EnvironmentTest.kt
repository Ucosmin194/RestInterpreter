package model

import com.rest.interpreter.model.Environment
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class EnvironmentTest {

    @Test
    fun testEnvironment() {
        val environment = Environment("1", "2", mutableMapOf("key" to "value"))
        assertEquals("1", environment.environmentId)
        assertEquals("2", environment.workspaceId)
        assertEquals(mutableMapOf("key" to "value"), environment.variables)
    }
}