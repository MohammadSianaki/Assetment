package com.github.mohammadsianaki.currencyexchange.di

import com.github.mohammadsianaki.currencyexchange.data.CurrencyExchangeRepository
import com.github.mohammadsianaki.currencyexchange.data.CurrencyExchangeRepositoryImpl
import com.github.mohammadsianaki.currencyexchange.data.local.BalanceDataSource
import com.github.mohammadsianaki.currencyexchange.data.local.LocalBalanceDataSource
import com.github.mohammadsianaki.currencyexchange.data.memory.ExchangeRateDataStore
import com.github.mohammadsianaki.currencyexchange.data.memory.InMemoryExchangeRateDataStore
import com.github.mohammadsianaki.currencyexchange.data.remote.ExchangeRateDataSource
import com.github.mohammadsianaki.currencyexchange.data.remote.RemoteExchangeRateDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    abstract fun bindAppRepository(impl: CurrencyExchangeRepositoryImpl): CurrencyExchangeRepository

    @Binds
    abstract fun bindRemoteExchangeDataSource(impl: RemoteExchangeRateDataSource): ExchangeRateDataSource

    @Binds
    abstract fun bindLocalBalanceDataSource(impl: LocalBalanceDataSource): BalanceDataSource

    @Binds
    abstract fun bindInMemoryExchangeRateDataStore(impl: InMemoryExchangeRateDataStore): ExchangeRateDataStore
}