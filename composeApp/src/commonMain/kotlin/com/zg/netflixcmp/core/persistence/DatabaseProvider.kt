package com.zg.netflixcmp.core.persistence

// Database Provider
object DatabaseProvider {
    internal var database: Database? = null

    fun initDatabase(databaseDriverFactory: DatabaseDriverFactory) {
        database = Database(databaseDriverFactory)
    }
}