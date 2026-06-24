package com.himanshumehra.kumaonfresh.presentation.ui.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.himanshumehra.kumaonfresh.presentation.ui.common.LoadingScreen

@Composable
fun CartDetailScreen(
    onBackClick: () -> Unit,
    viewModel: CartDetailViewModel = hiltViewModel(),
    addQuantityToCartViewModel: AddQuantityToCartViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val addQuantityUiState by addQuantityToCartViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(addQuantityUiState) {
        when (addQuantityUiState) {
            AddQuantityToCartViewModel.UiState.Idle -> {}
            AddQuantityToCartViewModel.UiState.Loading -> {}
            is AddQuantityToCartViewModel.UiState.Success -> {
                viewModel.loadCart()
                addQuantityToCartViewModel.resetState()
            }

            is AddQuantityToCartViewModel.UiState.Error -> {
                // Handle error state if needed
                addQuantityToCartViewModel.resetState()
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadCart()
    }

    when (val state = uiState) {
        CartDetailUiState.Idle,
        CartDetailUiState.Loading -> {
            LoadingScreen()
        }

        is CartDetailUiState.Error -> {
            Text(
                text = state.message,
                color = MaterialTheme.colorScheme.error
            )
        }

        is CartDetailUiState.Success -> {
            CartDetailScaffold(
                uiState = state,
                onBackClick = onBackClick,
                onAddClick = { itemId ->
                    addQuantityToCartViewModel.addQuantityToCart(
                        itemId = itemId,
                        quantity = 1
                    )
                },
                onRemoveClick = { itemId ->
                    addQuantityToCartViewModel.removeQuantityFromCart(
                        itemId = itemId,
                        quantity = 1
                    )
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CartDetailScaffold(
    uiState: CartDetailUiState.Success,
    onBackClick: () -> Unit,
    onAddClick: (String) -> Unit,
    onRemoveClick: (String) -> Unit
) {
    val cartItems = uiState.cart.data.orEmpty()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Cart Details")
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = cartItems,
                key = { it.itemId }
            ) { item ->

                CartItemRow(
                    item = item,
                    onAddClick = {
                        onAddClick(item.itemId)
                    },
                    onRemoveClick = {
                        onRemoveClick(item.itemId)
                    }
                )
            }
        }
    }
}