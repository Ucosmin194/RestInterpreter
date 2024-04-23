package com.rest.interpreter.model

data class Environment(
    val environmentId: String,
    val workspaceId: String,
    val variables: MutableMap<String, String> = mutableMapOf() // JSON string or another encoding of key-value pairs
) {
    fun addVariable(key:String, value: String) {
        variables[key] = value
    }
    fun removeVariable(key: String) {
        variables.remove(key)
    }
    fun listVariables(): Map<String, String> = variables
}
