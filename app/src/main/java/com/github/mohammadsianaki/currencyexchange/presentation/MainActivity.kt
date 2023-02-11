package com.github.mohammadsianaki.currencyexchange.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.github.mohammadsianaki.currencyexchange.presentation.ui.screens.ExchangeScreen
import com.github.mohammadsianaki.currencyexchange.presentation.ui.theme.CurrencyExchangeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CurrencyExchangeTheme {
                // A surface container using the 'background' color from the theme
                ExchangeScreen()
            }
        }
    }
}