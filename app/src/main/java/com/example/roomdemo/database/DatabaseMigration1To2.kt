package com.example.roomdemo.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class DatabaseMigration1To2 : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Add the new "age" column to the "users" table.
        database.execSQL("ALTER TABLE users ADD COLUMN age INTEGER DEFAULT NULL") // this function executes sql query without returning anything
    }
}