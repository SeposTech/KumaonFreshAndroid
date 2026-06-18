package com.himanshumehra.kumaonfresh.presentation.ui.home

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.himanshumehra.kumaonfresh.data.remote.api.dto.response.CategoryData
import com.himanshumehra.kumaonfresh.presentation.ui.common.LoadingScreen

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {
    when (val uiState = viewModel.uiState.collectAsState().value) {
        is HomeViewModel.UiState.Idle -> {

        }

        is HomeViewModel.UiState.Loading -> {
            LoadingScreen()
        }

        is HomeViewModel.UiState.Success -> {
            LoadCategory(navController, categoryList = uiState.data)
        }

        is HomeViewModel.UiState.Error -> {
            // Show error message
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoadCategory(navController: NavController, categoryList: List<CategoryData>?) {
    Surface(modifier = Modifier.fillMaxSize()) {

        Scaffold(topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Category List") },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Red,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }) { padding ->

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                items(categoryList ?: emptyList()) { category ->
                    CategoryRowScreen(
                        imageUrl = category.categoryImage,
                        categoryName = category.categoryName,
                        onClick = {
                            navController.navigate("item/${category.CategoryId}")
                        }
                    )
                }
            }
        }


    }
}