package com.rest.interpreter.repository

import com.rest.interpreter.model.PersistentTab
import com.rest.interpreter.persistence.Persistence

class TabRepository {
    infix fun saveTab(tab: PersistentTab) {
        Persistence.save(tab)
    }

    fun getTabs(): List<PersistentTab> {
        return Persistence.getTabs()
    }
}