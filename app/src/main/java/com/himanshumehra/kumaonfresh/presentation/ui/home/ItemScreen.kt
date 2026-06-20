package com.himanshumehra.kumaonfresh.presentation.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.himanshumehra.kumaonfresh.data.remote.api.dto.response.ItemData
import com.himanshumehra.kumaonfresh.presentation.ui.cart.AddToCartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemScreen(
    onBackClick: () -> Unit,
    onCartClick: () -> Unit,
    cartViewModel: AddToCartViewModel = hiltViewModel(),
    viewModel: ItemViewModel = hiltViewModel()
) {
    val items by viewModel.items.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val cartUiState by cartViewModel.uiState.collectAsState()

    ItemScreenContent(
        items = items,
        isLoading = isLoading,
        cartUiState = cartUiState,
        onBackClick = onBackClick,
        onCartClick = onCartClick,
        onAddClick = {
            viewModel.incrementQuantity(it)
            cartViewModel.addToCart(
                itemId = it.itemId.toString(),
                itemQuantity = it.itemQuantity + 1,
                itemPrice = it.itemPrice
            )
        },
        onRemoveClick = {
            viewModel.decrementQuantity(it)
            cartViewModel.addToCart(
                itemId = it.itemId.toString(),
                itemQuantity = it.itemQuantity - 1,
                itemPrice = it.itemPrice
            )
        },
        onResetCartState = { cartViewModel.resetState() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemScreenContent(
    items: List<ItemData>,
    isLoading: Boolean,
    cartUiState: Any?,
    onBackClick: () -> Unit,
    onCartClick: () -> Unit,
    onAddClick: (ItemData) -> Unit,
    onRemoveClick: (ItemData) -> Unit,
    onResetCartState: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val cartItemCount = items.sumOf { it.itemQuantity }

    // Show snackbar when cart state changes
    LaunchedEffect(cartUiState) {
        when (cartUiState) {
            is AddToCartViewModel.UiState.Success -> {
                snackbarHostState.showSnackbar(
                    message = cartUiState.message,
                    duration = SnackbarDuration.Short
                )
                onResetCartState()
            }
            is AddToCartViewModel.UiState.Error -> {
                snackbarHostState.showSnackbar(
                    message = cartUiState.error,
                    duration = SnackbarDuration.Short
                )
                onResetCartState()
            }
            else -> {}
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Items") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onCartClick) {
                        BadgedBox(
                            badge = {
                                if (cartItemCount > 0) {
                                    Badge {
                                        Text(text = cartItemCount.toString())
                                    }
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = "Open cart"
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(8.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(items) { item ->
                        ItemRow(
                            item = item,
                            onAddClick = onAddClick,
                            onRemoveClick = onRemoveClick
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemScreenPreview() {
    ItemScreenContent(
        items = listOf(
            ItemData(1, "Desc", 1, "", "Item 1", 10.0, 0),
            ItemData(1, "Desc", 2, "", "Item 2", 20.0, 5)
        ),
        isLoading = false,
        cartUiState = AddToCartViewModel.UiState.Idle,
        onBackClick = {},
        onCartClick = {},
        onAddClick = {},
        onRemoveClick = {},
        onResetCartState = {}
    )
}
