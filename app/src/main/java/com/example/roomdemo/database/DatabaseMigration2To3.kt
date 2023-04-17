package com.example.roomdemo.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class DatabaseMigration2To3 : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Add the new "hehe" column to the "users" table.
        database.execSQL("ALTER TABLE users ADD COLUMN hehe INTEGER NOT NULL DEFAULT 1") // use 1 or 0 for boolean always, not true or false
    }
}
