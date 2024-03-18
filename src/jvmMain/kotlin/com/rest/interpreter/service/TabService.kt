package com.rest.interpreter.service

import com.rest.interpreter.model.PersistentTab
import com.rest.interpreter.repository.TabRepository

class TabService {
    private val repository = TabRepository()

    fun saveTab(tab: PersistentTab) {
        repository saveTab(tab)
    }

    fun getTabs(): List<PersistentTab> {
        return repository.getTabs()
    }

}