package com.rest.interpreter.model

data class Collection(
    val collectionId: String,
    var name: String,
    val workspaceId: String,
    val userId: String,
    val requests: MutableList<Request> = mutableListOf()
) {
    fun addRequest(request: Request) {
        requests.add(request)
    }
    fun removeRequest(requestId: String) {
        requests.removeAll { it.requestId == requestId }
    }
    fun listRequests(): List<Request> = requests
}
