package com.github.mohammadsianaki.currencyexchange.presentation.ui.screens

import androidx.lifecycle.ViewModel
import com.github.mohammadsianaki.currencyexchange.data.CurrencyExchangeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExchangeViewModel @Inject constructor(
    private val repository: CurrencyExchangeRepository
) : ViewModel() {}