package com.github.mohammadsianaki.currencyexchange.data.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo


@Entity(tableName = "user_balance")
data class LocalBalanceEntity(
    @PrimaryKey val symbol: String,
    @ColumnInfo(name = "amount") val amount: Double,
)