package com.github.mohammadsianaki.currencyexchange.di

import android.content.Context
import androidx.room.Room
import com.github.mohammadsianaki.currencyexchange.data.local.db.AppDatabase
import com.github.mohammadsianaki.currencyexchange.data.local.db.UserBalanceDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideRoomDb(@ApplicationContext context: Context): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, AppDatabase.DB_NAME)
            .build()
    }

    @Provides
    fun provideBalanceDao(appDatabase: AppDatabase): UserBalanceDao {
        return appDatabase.balanceDao()
    }
}