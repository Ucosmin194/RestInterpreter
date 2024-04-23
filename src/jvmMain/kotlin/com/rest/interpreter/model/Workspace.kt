package com.rest.interpreter.model

data class Workspace(
    val workspaceId: String,
    var name: String,
    var description: String,
    val collections: MutableList<Collection> = mutableListOf()
) {
    fun addCollection(collection: Collection) {
        collections.add(collection)
    }
    fun removeCollection(collectionId: String) {
        collections.removeAll { it.collectionId == collectionId }
    }
    fun listCollection(): List<Collection> = collections
}
