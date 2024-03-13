package com.db.dbpostman.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import com.db.dbpostman.model.PersistentTab

object TabContentFactory {
    @Composable
    fun TabContent(screen: @Composable () -> Unit) {
        Box() {
            screen()
        }
    }

    data class TabItem(var title: String, val persistentTab: PersistentTab, val screen: @Composable () -> Unit)

}