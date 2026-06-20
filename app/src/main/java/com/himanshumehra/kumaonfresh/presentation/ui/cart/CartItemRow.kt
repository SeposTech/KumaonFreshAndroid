package com.himanshumehra.kumaonfresh.presentation.ui.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.himanshumehra.kumaonfresh.data.remote.api.dto.response.CartItem
import com.himanshumehra.kumaonfresh.utils.Constants.ITEM_IMAGE_BASE_URL

@Composable
fun CartItemRow(
    item: CartItem,
    onAddClick: (CartItem) -> Unit,
    onRemoveClick: (CartItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        tonalElevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = ITEM_IMAGE_BASE_URL + item.itemImage),
                contentDescription = item.itemName,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = item.itemName,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(
                    onClick = { onRemoveClick(item) },
                    enabled = item.itemQuantity > 0
                ) {
                    Icon(
                        imageVector = Icons.Default.Remove,
                        contentDescription = "Decrease quantity"
                    )
                }

                Text(
                    text = item.itemQuantity.toString(),
                    style = MaterialTheme.typography.titleMedium
                )

                IconButton(onClick = { onAddClick(item) }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Increase quantity"
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Cart Item Row")
@Composable
private fun CartItemRowPreview() {
    val sampleItem = CartItem(
        categoryId = "cat_1",
        itemDescription = "Fresh and organic apples",
        itemId = "item_1",
        itemImage = "https://picsum.photos/200",
        itemName = "Red Apple",
        itemPrice = 120.0,
        itemQuantity = 2
    )

    MaterialTheme {
        CartItemRow(
            item = sampleItem,
            onAddClick = {},
            onRemoveClick = {}
        )
    }
}
