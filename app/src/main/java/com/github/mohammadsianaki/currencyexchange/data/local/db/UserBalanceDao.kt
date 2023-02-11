package com.github.mohammadsianaki.currencyexchange.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface UserBalanceDao {
    @Query("SELECT * FROM user_balance")
    suspend fun getAll(): List<LocalBalanceEntity>

    @Insert
    suspend fun saveAll(balance: List<LocalBalanceEntity>)
}