package com.example.roomdemo.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase


@androidx.room.Database(entities = [User::class], version = 3)
abstract class Database : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: Database? = null
        private val MIGRATION_1_TO_2 = DatabaseMigration1To2()
        private val MIGRATION_2_TO_3 = DatabaseMigration2To3()

        fun getDatabase(context: Context): Database {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    Database::class.java,
                    "my_database"
                )
                    .addMigrations(MIGRATION_1_TO_2, MIGRATION_2_TO_3)
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
