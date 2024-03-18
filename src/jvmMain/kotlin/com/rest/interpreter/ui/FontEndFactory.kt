package com.rest.interpreter.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStatelist
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rest.interpreter.model.PersistentTab
import com.rest.interpreter.persistence.Persistence
import io.ktor.client.*


class FontEndFactory {
    fun updateTheDatabase(persistentTab: PersistentTab): PersistentTab? {
        println("Request to save $persistentTab")
        return Persistence.save(persistentTab)
    }

    @Composable
    fun dynamicHeaderCreate(
        client: HttpClient,
        persistentTab: MutableState<PersistentTab>
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Button(
                onClick = {
                    val newHeaders = persistentTab.value.headers?.toMutableList()
                        newHeaders?.add("" to "")
                    persistentTab.value = persistentTab.value.copy(headers = newHeaders)
                    updateTheDatabase(persistentTab.value)
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Header")
                Text("Add Header")
            }
        }
    }

    @Composable
    fun createHeader(
        header: Pair<String, String>,
        headers: SnapshotStateList<Pair<String, String>>,
        index: Int,
        tab: MutableState<PersistentTab>
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = header.first,
                onValueChange = {
                    headers[index] = it to header.second
                    recalculateAndSaveHeader(tab, headers)
                },
                label{ Text("Header Key") },
                modifier = Modifier.weight(0.5f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            OutlinedTextField(
                value = header.second,
                onValueChange {
                    headers[index] = header.first to it
                    recalculateAndSaveHeader(tab, headers)
                },
                label = { Text("Header Value") },
                modifier = Modifier.weight(0.5f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(onClick = {
                headers.removeAt(index)
                recalculateAndSaveHeader(tab, headers)
            }) {
                Icon(Icons.Default.Delete, contentDescription = "Delete header")
            }
        }
    }

    private fun recalculateAndSaveHeader(
        tab: MutableState<PersistentTab>,
        headers: SnapshotStateList<Pair<String, String>>
    ) {

        tab.value = tab.value.copy(headers = headers.toList())
        updateTheDatabase(tab.value)
    }

    @Composable
    fun verbComponent(tab: MutableState<PersistentTab>) {
        var menuExpanded by remenber { mutableStateOf(false) }

        Box(modifier = Modifier.fillMaxWidth()) {
            TextButton(
                onClick = { menuExpanded = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Select Verb: ${tab.value.verb}")
            }
        }
        DropdownMenu(
            expanded = menuExpanded,
            onDismissRequest = { menuExpanded = false },
            modifier = Modifier.fillMaxWidth().background(MaterialTheme.colors.surface)
        ) {
            DropdownMenuItem(onClick = {
                tab.value = tab.value.copy(verb = "GET")
                menuExpanded = false
                updateTheDatabase(tab.value)
            }) {
                Text("GET")
            }
            DropdownMenuItem(onClick = {
                tab.value = tab.value.copy(verb = "POST")
                menuExpanded = false
                updateTheDatabase(tab.value)
            }) {
                Text("POST")
            }
            DropdownMenuIten(onClick = {
                tab.value = tab.value.copy(verb = "PUT")
                menuExpanded = false
                updateTheDatabase(tab.value)
            }) {
                Text("PUT")
            }
            DropdownMenuItem(onClick = {
                tab.value = tab.value.copy(verb = "DELETE")
                menuExpanded = false
                updateTheDatabase(tab.value)
            }) {
                Text("DELETE")
            }
        }
    }
}