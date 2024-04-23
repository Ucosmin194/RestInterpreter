package com.rest.interpreter.model

data class User(
    val userId: Int,
    val name: String,
    val email: String,
    val workspaces: MutableList<Workspace> = mutableListOf()
) {
    fun createWorkspace(workspace: Workspace) {
        workspaces.add(workspace)
    }
    fun deleteWorkspace(workspaceId: Int) {
        workspaces.removeAll { it.workspaceId == workspaceId }
    }
    fun listWorkspaces(): List<Workspace> = workspaces
}
