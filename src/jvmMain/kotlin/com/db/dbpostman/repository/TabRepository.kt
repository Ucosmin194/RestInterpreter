package com.db.dbpostman.repository

import com.db.dbpostman.model.PersistentTab
import com.db.dbpostman.persistence.Persistence

class TabRepository {
    fun saveTab(tab: PersistentTab) {
        Persistence.save(tab)
    }

    fun getTabs(): List<PersistentTab> {
        return Persistence.getTabs()
    }
}