package com.github.mohammadsianaki.currencyexchange.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [LocalBalanceEntity::class], version = AppDatabase.DB_VERSION)
abstract class AppDatabase : RoomDatabase() {
    abstract fun balanceDao(): UserBalanceDao

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "app-db"
    }
}