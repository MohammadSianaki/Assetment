package com.github.mohammadsianaki.currencyexchange.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface UserBalanceDao {
    @Query("SELECT * FROM user_balance")
    suspend fun getAll(): List<LocalBalanceEntity>

    @Query("SELECT * FROM user_balance")
    fun observeAll(): Flow<List<LocalBalanceEntity>>

    @Insert
    suspend fun saveAll(balance: List<LocalBalanceEntity>)
}