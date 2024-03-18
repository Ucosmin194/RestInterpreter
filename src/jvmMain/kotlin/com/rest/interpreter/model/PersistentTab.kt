package com.rest.interpreter.model

data class PersistentTab(
    val id: Int,
    val title: String,
    val url: String,
    val verb: String,
    val requestBody: String?,
    val content: String?,
    var headers: List<Pair<String, String>>?
)