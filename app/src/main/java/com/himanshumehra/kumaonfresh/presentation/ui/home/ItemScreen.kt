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
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.himanshumehra.kumaonfresh.data.remote.api.dto.response.ItemData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemScreen(
    onBackClick: () -> Unit,
    viewModel: ItemViewModel = hiltViewModel()
) {
    val items by viewModel.items.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    ItemScreenContent(
        items = items,
        isLoading = isLoading,
        onBackClick = onBackClick,
        onAddClick = { viewModel.incrementQuantity(it) },
        onRemoveClick = { viewModel.decrementQuantity(it) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemScreenContent(
    items: List<ItemData>,
    isLoading: Boolean,
    onBackClick: () -> Unit,
    onAddClick: (ItemData) -> Unit,
    onRemoveClick: (ItemData) -> Unit
) {
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
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
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
        onBackClick = {},
        onAddClick = {},
        onRemoveClick = {}
    )
}
