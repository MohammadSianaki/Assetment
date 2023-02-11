package com.github.mohammadsianaki.currencyexchange.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.mohammadsianaki.currencyexchange.R


@Composable
fun ExchangeScreen() {
    Scaffold(topBar = { AppTitle() }, bottomBar = { SubmitButton(onClick = {}) }) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(32.dp, Alignment.Top)
        ) {
            item {
                MyBalances()
            }
            item {
                CurrencyExchange()
            }
        }
    }
}


@Composable
private fun LazyItemScope.MyBalances() {
    Column(verticalArrangement = Arrangement.spacedBy(32.dp)) {
        Text(text = stringResource(R.string.my_balances))
        LazyRow {
            items(10) {
                Text(text = "1000.00 EUR")
            }
        }
    }
}

@Composable
private fun LazyItemScope.CurrencyExchange() {

}


@Composable
private fun AppTitle() {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Text(text = stringResource(R.string.currency_converter))
    }
}

@Composable
private fun SubmitButton(onClick: () -> Unit) {
    Button(
        shape = RoundedCornerShape(100),
        elevation = null,
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        Text(text = stringResource(R.string.submit))
    }
}