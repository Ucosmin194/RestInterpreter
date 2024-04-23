package model

import com.rest.interpreter.model.User
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class UserTest {
    @Test
    fun testUser() {
        val user = User(1, "Test User", "test@example.com", mutableListOf())
        assertEquals(1, user.userId)
        assertEquals("Test User", user.name)
    }
}