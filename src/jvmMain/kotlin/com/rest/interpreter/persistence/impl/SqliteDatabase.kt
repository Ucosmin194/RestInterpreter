package com.rest.interpreter.persistence.impl

import com.rest.interpreter.model.PersistentTab
import com.rest.interpreter.persistence.Database
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

class SqliteDatabase : Database {
    override fun createTable() {
        val connection: Connection = DriverManager.getConnection("jdbc:sqlite:database.db")
        val statement: Statement = connection.createStatement()

        statement.execute("DROP TABLE IF EXISTS tabs")

        val createTableQuery = """
                                CREATE TABLE IF NOT EXISTS tabs (
                                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                                    title TEXT NOT NULL,
                                    url TEXT NOT NULL,
                                    verb TEXT NOT NULL, 
                                    requestBody TEXT,
                                    content TEXT, 
                                    headers TEXT
                                );
                            """

        statement.executeUpdate(createTableQuery)

        statement.close()
        connection.close()

    }

    override fun save(tab: PersistentTab): PersistentTab? {
        val connection: Connection = DriverManager.getConnection("jdbc:sqlite:database.db")
        val statement: Statement = connection.createStatement()
        val gson = Gson()
        val headers = gson.toJson(tab.headers)
        val insertQuery = """
                            INSERT INTO tabs (title, url, verb, requestBody, content, headers) 
                            VALUES ('${tab.title}', '${tab.url}', '${tab.verb}', '${tab.requestBody}', '${tab.requestBody}', '$headers')
                        """

        statement.executeUpdate(insertQuery)

        val generatedKeys = statement.generatedKeys
        generatedKeys.next()
        val id = generatedKeys.getInt(1)

        statement.close()
        connection.close()
        return getTabById(id)
    }

    override fun getTabById(id: Int): PersistentTab? {
        val connection: Connection = DriverManager.getConnection("jdbc:sqlite:database.db")
        val statement: Statement = connection.createStatement()
        val selectQuery = "SELECT * FROM tabs WHERE id = $id"
        val resultSet = statement.executeQuery(selectQuery)
        var tab: PersistentTab? = null

        if (resultSet.next()) {
            tab = createTabFromResultSet(resultSet)
        }

        resultSet.close()
        statement.close()
        connection.close()

        return tab
    }

    override fun delete(persistentTab: PersistentTab) {
        val connection: Connection = DriverManager.getConnection("jdbc:sqlite:database.db")
        val statement: Statement = connection.createStatement()
        val selectQuery = "DELETE FROM tabs WHERE id = ${persistentTab.id}"
        val deleteSuccessful = statement.execute(selectQuery)
        println("Deleted $persistentTab $deleteSuccessful")
        statement.close()
        connection.close()
    }

    override fun getTabs(): List<PersistentTab> {
        val connection: Connection = DriverManager.getConnection("jdbc:sqlite:database.db")
        val statement: Statement = connection.createStatement()

        val selectQuery = """
                            SELECT * FROM tabs;
                        """
        val resultSet = statement.executeQuery(selectQuery)
        val tabs = mutableListOf<PersistentTab>()

        while (resultSet.next()) {
            val tab = createTabFromResultSet(resultSet)
            tabs.add(tab)
        }

        resultSet.close()
        statement.close()
        connection.close()

        return tabs
    }

    private fun createTabFromResultSet(
        resultSet: ResultSet
    ): PersistentTab {
        val id = resultSet.getInt("id")
        val title = resultSet.getString("title")
        val url = resultSet.getString("url")
        val verb = resultSet.getString("verb")
        val requestBody = resultSet.getString("requestBody")
        val content = resultSet.getString("content")

        val headersJson = resultSet.getString("headers")

        val gson = Gson()
        val headersType = object : TypeToken<List<Pair<String, String>>>() {}.type
        val headers = gson.fromJson<List<Pair<String, String>>>(headersJson, headersType)

        return PersistentTab(id, title, url, verb, requestBody, content, headers)
    }

    //fixme fix update function
    override fun update(tab: PersistentTab): PersistentTab? {
        val connection: Connection = DriverManager.getConnection("jdbc:sqlite:database.db")
        val statement: Statement = connection.createStatement()
        val gson = Gson()
        val headersJson = gson.toJson(tab.headers)

        val updateQuery = """
                            UPDATE tabs
                            SET title = '${tab.title}', 
                                url = '${tab.url}',
                                verb = '${tab.verb}',
                                requestBody = '${tab.requestBody}',
                                content = '${tab.content}',
                                headers = '$headersJson'
                            WHERE id = ${tab.id}
                          """
        statement.executeUpdate(updateQuery)

        statement.close()
        connection.close()

        return getTabById(tab.id)
    }
}
