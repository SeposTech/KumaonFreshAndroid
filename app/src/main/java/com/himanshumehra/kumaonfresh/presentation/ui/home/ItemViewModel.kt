package com.himanshumehra.kumaonfresh.presentation.ui.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.himanshumehra.kumaonfresh.data.remote.api.dto.response.Item
import com.himanshumehra.kumaonfresh.domain.usecase.ItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val itemUseCase: ItemUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val catId = savedStateHandle.get<Long>("catId")

    private val _items = MutableStateFlow<List<Item>>(emptyList())
    val items: StateFlow<List<Item>> = _items

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        fetchItems()
    }

    fun fetchItems() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = itemUseCase(catId ?: 0L)
                _items.value = response.data ?: emptyList()
            } catch (e: Exception) {
                // Handle error
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun incrementQuantity(item: Item) {
        val currentList = _items.value.toMutableList()
        val index = currentList.indexOfFirst { it.itemId == item.itemId }
        if (index != -1) {
            val updatedItem = currentList[index].copy(itemQuantity = currentList[index].itemQuantity + 1)
            currentList[index] = updatedItem
            _items.value = currentList
        }
    }

    fun decrementQuantity(item: Item) {
        val currentList = _items.value.toMutableList()
        val index = currentList.indexOfFirst { it.itemId == item.itemId }
        if (index != -1 && currentList[index].itemQuantity > 0) {
            val updatedItem = currentList[index].copy(itemQuantity = currentList[index].itemQuantity - 1)
            currentList[index] = updatedItem
            _items.value = currentList
        }
    }
}
