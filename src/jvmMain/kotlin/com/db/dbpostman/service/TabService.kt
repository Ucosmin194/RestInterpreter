package com.db.dbpostman.service

import com.db.dbpostman.model.PersistentTab
import com.db.dbpostman.repository.TabRepository

class TabService {
    private val repository = TabRepository()

    fun saveTab(tab: PersistentTab) {
        repository saveTab(tab)
    }

    fun getTabs(): List<PersistentTab> {
        return repository.getTabs()
    }

}