package com.github.mohammadsianaki.currencyexchange.presentation.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.github.mohammadsianaki.currencyexchange.presentation.ui.theme.CurrencyExchangeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExchangeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                CurrencyExchangeTheme {
                    ExchangeScreen()
                }
            }
        }
    }
}