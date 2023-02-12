package com.github.mohammadsianaki.currencyexchange.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.mohammadsianaki.currencyexchange.R
import com.github.mohammadsianaki.currencyexchange.domain.BalanceEntity
import com.github.mohammadsianaki.currencyexchange.domain.ExchangeRateEntity
import kotlinx.coroutines.Dispatchers


@Composable
fun ExchangeScreen(exchangeViewModel: ExchangeViewModel = viewModel()) {
    val uiState =
        exchangeViewModel.uiState.collectAsStateWithLifecycle(context = Dispatchers.Main.immediate).value
    Scaffold(topBar = { AppTitle() }, bottomBar = { SubmitButton(onClick = {}) }) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(32.dp, Alignment.Top)
        ) {
            item {
                uiState.userBalance.onLoaded { balanceEntities ->
                    MyBalances(balanceEntities)
                }
                uiState.userBalance.onLoading {
                    LoadingView()
                }
            }
            item {
                uiState.exchangeRates.onLoaded { exchangeRateEntities ->
                    CurrencyExchange(exchangeRateEntities)
                }
                uiState.exchangeRates.onLoading {
                    LoadingView()
                }
            }
        }
    }
}

@Composable
private fun LoadingView() {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}


@Composable
private fun MyBalances(balanceEntities: List<BalanceEntity>) {
    Column(
        verticalArrangement = Arrangement.spacedBy(32.dp),
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Text(text = stringResource(R.string.my_balances))
        LazyRow {
            itemsIndexed(
                items = balanceEntities,
                key = { index: Int, item: BalanceEntity -> index },
            ) { index: Int, item: BalanceEntity ->
                BalanceItem(balanceEntity = item)
            }
        }
    }
}

@Composable
private fun BalanceItem(balanceEntity: BalanceEntity) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start)
    ) {
        Text(text = balanceEntity.amount.toString())
        Text(text = balanceEntity.symbol)
    }
}

@Composable
private fun CurrencyExchange(exchangeRateEntities: List<ExchangeRateEntity>) {
    Column(
        verticalArrangement = Arrangement.spacedBy(32.dp),
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Text(text = stringResource(R.string.currency_exchange))

    }
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