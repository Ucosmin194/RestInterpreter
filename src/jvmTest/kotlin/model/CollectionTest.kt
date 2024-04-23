package model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import com.rest.interpreter.model.Collection

class CollectionTest {

    @Test
    fun testCollection() {
        val collection = Collection("1", "Test Collection", "1", "1")
        assertEquals("1", collection.collectionId)
        assertEquals("Test Collection", collection.name)
        assertEquals("1", collection.workspaceId)
        assertEquals("1", collection.userId)
    }
}