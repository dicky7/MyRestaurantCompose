package com.example.myrestaurantcompose.ui.components


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myrestaurantcompose.data.remote.response.RestaurantsItem
import com.example.myrestaurantcompose.util.Static.BASE_URL
import com.example.myrestaurantcompose.util.Static.SMALL_IMAGE_URL

@Composable
fun RestaurantContentItem(
    restaurants: RestaurantsItem,
    modifier: Modifier = Modifier) {

    Card(
        modifier = modifier.width(170.dp),
        elevation = 6.dp,
        shape = RoundedCornerShape(10.dp)) {

        Column {
            AsyncImage(
                model = "$BASE_URL$SMALL_IMAGE_URL${restaurants.pictureId}",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            Text(
                    text = restaurants.name,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.ExtraBold)
                )
        }

    }
}

@Composable
@Preview(showBackground = true)
fun MenuItemPreview() {
    MaterialTheme {
        RestaurantContentItem(
            restaurants = RestaurantsItem(
                id = "rqdv5juczeskfw1e867",
                name = "Melting Pot",
                description = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. ...",
                pictureId = "14",
                city = "medan",
                rating = 4.2
            ),
            modifier = Modifier.padding(8.dp)
        )
    }
}
