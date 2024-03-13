package com.db.dbpostman.external.rest

import androidx.compose.runtime.MutableState
import com.db.dbpostman.model.PersistentTab
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import java.net.http.HttpClient

object RestClient {
    suspend fun sendRequest(
        client: HttpClient,
        tab: MutableState<PersistentTab>
    ): String {
        return try {
            client.request<String> {
                this.method = HttpMethod.parse(tab.value.verb)
                this.url(tab.value.url)
                this.headers {
                    tab.value.headers?.forEach { header ->
                        append(header.first, header.second)
                    }
                }
                if (tab.value.verb == "POST" || tab.value.verb == "PUT") {
                    this.body = tab.value.requestBody!!
                }
            }
        } catch (e: Throwable) {
            "Error: ${e.message}"
        }
    }
}