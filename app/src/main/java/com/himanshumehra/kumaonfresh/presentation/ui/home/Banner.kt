package com.himanshumehra.kumaonfresh.presentation.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.himanshumehra.kumaonfresh.data.remote.api.dto.response.Banner
import com.himanshumehra.kumaonfresh.utils.Constants.ITEM_IMAGE_BASE_URL

@Composable
fun BannerScreen(
    banners: List<Banner>,
    modifier: Modifier = Modifier
) {
    if (banners.isEmpty()) {
        return
    }

    val pagerState = rememberPagerState(pageCount = { banners.size })

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        // Horizontal Pager for images
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) { page ->
            BannerImage(
                banner = banners[page]
            )
        }

        // Page indicators (dots)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(banners.size) { index ->
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .height(8.dp)
                        .fillMaxWidth(if (pagerState.currentPage == index) 0.3f else 0.08f)
                        .clip(CircleShape)
                        .background(
                            if (pagerState.currentPage == index)
                                Color.Black
                            else
                                Color.Gray
                        )
                )
            }
        }
    }
}

@Composable
private fun BannerImage(
    banner: Banner,
    modifier: Modifier = Modifier
) {
    val imageUrl = ITEM_IMAGE_BASE_URL + banner.bannerImage

    SubcomposeAsyncImage(
        model = imageUrl,
        contentDescription = "Banner ${banner.bannerId}",
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp),
        contentScale = ContentScale.Crop,
        loading = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        },
        error = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                // Error placeholder
            }
        }
    )
}