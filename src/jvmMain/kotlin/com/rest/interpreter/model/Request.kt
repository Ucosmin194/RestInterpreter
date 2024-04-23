package com.rest.interpreter.model

data class Request(
    val requestId: String,
    val collectionId: String,
    val method: String,
    val url: String,
    val headers: MutableMap<String, String> = mutableMapOf(), // JSON string or another encoding if necessary
    val body: String? // JSON string or another encoding if necessary, nullable if not required
) {
    fun execute(): Response {
        // Execute the request and return the response
        TODO() // Implement this function, it's just a placeholder now
        return Response(data = "Response data")
    }
    fun saveResponse(response: Response) {
        // Save the response
    }
}

data class Response(
    val data: String
)
