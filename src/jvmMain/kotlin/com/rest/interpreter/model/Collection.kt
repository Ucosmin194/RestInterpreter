package com.rest.interpreter.model

data class Collection(
    val collectionId: Int,
    var name: String,
    val workspaceId: Int,
    val userId: Int,
    val requests: MutableList<Request> = mutableListOf()
) {
    fun addRequest(request: Request) {
        requests.add(request)
    }
    fun removeRequest(requestId: Int) {
        requests.removeAll { it.requestId == requestId }
    }
    fun listRequests(): List<Request> = requests
}
