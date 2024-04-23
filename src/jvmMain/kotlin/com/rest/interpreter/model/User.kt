package com.rest.interpreter.model

data class User(
    val userId: String,
    val name: String,
    val email: String,
    val workspaces: MutableList<Workspace> = mutableListOf()
) {
    fun createWorkspace(workspace: Workspace) {
        workspaces.add(workspace)
    }
    fun deleteWorkspace(workspaceId: String) {
        workspaces.removeAll { it.workspaceId == workspaceId }
    }
    fun listWorkspaces(): List<Workspace> = workspaces
}
