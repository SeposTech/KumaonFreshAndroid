package com.himanshumehra.kumaonfresh.data.remote.api.dto.response

data class HomeResponse(
    val status: String,
    val message: String,
    val data: HomeData
)

data class HomeData(
    val banners: List<Banner>,
    val categories: List<Category>,
    val similarItems: List<Item>
)

data class Banner(
    val bannerId: Int,
    val bannerImage: String
)

data class Category(
    val categoryName: String,
    val categoryImage: String,
    val CategoryId: Int
)

data class Item(
    val categoryId: Int?,
    val itemDescription: String,
    val itemId: Int,
    val itemImage: String,
    val itemName: String,
    val itemPrice: Double,
    val itemQuantity: Int
)