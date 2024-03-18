package com.rest.interpreter.persistence

import com.rest.interpreter.model.PersistentTab

interface Database {
    fun createTable()

    fun save(tab: PersistentTab): PersistentTab?

    fun getTabs():List<PersistentTab>
    fun update(tab: PersistentTab) : PersistentTab?

    fun getTabById(id: Int): PersistentTab?
    fun delete(persistentTab: PersistentTab)
}