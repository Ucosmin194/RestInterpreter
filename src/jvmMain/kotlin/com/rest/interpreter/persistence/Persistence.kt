package com.rest.interpreter.persistence

import com.rest.interpreter.model.PersistentTab
import com.rest.interpreter.persistence.impl.SqliteDatabase

class Persistence(database: SqliteDatabase) {

    companion object {
        private val database = SqliteDatabase()

        /**
        Create the store for the tabs and other data to be stored
         */
        fun createDatabase() {
            database.createTable()
            if (database.getTabs().isEmpty()) {
                save(
                    PersistentTab(
                        0, "eid-uat", "https://",
                        "POST", "grant type=client_credentials", "",
                        listOf(
                            "Content-Type" to "application/x-www-form-urlencoded",
                            "Authorization" to "Bearer token"
                        )
                    )
                )
            }
        }

        /**
        Save the tab to the database
         */
        fun save(tab: PersistentTab): PersistentTab? {
            return if (tab.id == 0)
                database.save(tab)
            else
                database.update(tab)
        }

        fun getTabs(): List<PersistentTab> {
            val tabs = database.getTabs()
            println("Returning $(tabs.size)")
            return tabs
        }

        fun getTabById(id: Int): PersistentTab {
            val tab = database.getTabById(id)
            println("Returning $(tab)")
            return tab!!
        }

        fun delete(persistentTab: PersistentTab) {
            database.delete(persistentTab)
        }
    }
}