package com.example.myrestaurantcompose.ui.screen.detail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.myrestaurantcompose.data.Result
import com.example.myrestaurantcompose.data.remote.response.Restaurant
import com.example.myrestaurantcompose.util.Static
import com.example.myrestaurantcompose.R
import com.example.myrestaurantcompose.data.remote.response.Menus

@Composable
fun DetailScreen(
    restaurantId: String,
    navigateBack: () -> Unit,
) {
    val viewModel = hiltViewModel<DetailViewModel>()
    viewModel.restaurantDetail.collectAsState(initial = Result.Loading).value.let { result ->
        when (result) {
            is Result.Loading -> {
                viewModel.getRestaurantDetail(restaurantId)
            }
            is Result.Success -> {
                DetailContent(
                    restaurant = result.data,
                    onBackClick = navigateBack
                )
            }
            is Result.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(result.error)
                }
            }

        }

    }
}

@Composable
fun DetailContent(
    restaurant: Restaurant,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Box {
            AsyncImage(
                model = "${Static.BASE_URL}${Static.SMALL_IMAGE_URL}${restaurant.pictureId}",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .height(400.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
            )
            Button(
                modifier = Modifier.padding(8.dp).width(50.dp).height(40.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray),
                onClick = { onBackClick() }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    tint = Color.White,
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = restaurant.name,
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.ExtraBold
                ),
            )
            Row(modifier.padding(top = 10.dp)) {
                Row(
                    modifier = modifier.weight(2f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = Color.Red
                    )
                    Text(
                        text = "${restaurant.city}, ${restaurant.address}",
                        style = MaterialTheme.typography.subtitle2.copy(color = Color.DarkGray)
                    )
                }
                Row(
                    modifier = modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color.Yellow
                    )
                    Text(
                        text = restaurant.rating.toString(),
                        style = MaterialTheme.typography.subtitle2.copy(color = Color.DarkGray)
                    )
                }
            }
            Spacer(
                modifier = Modifier
                    .padding(vertical = 15.dp)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.LightGray)
            )

            Text(
                text = stringResource(R.string.description),
                style = MaterialTheme.typography.h6.copy(
                    fontWeight = FontWeight.Medium
                ),
            )
            Text(
                text = restaurant.description,
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Justify,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DetailContentPreview() {
    MaterialTheme {
        DetailContent(
            restaurant = Restaurant(
                id = "rqdv5juczeskfw1e867",
                name = "Melting Pot",
                description = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. ...",
                pictureId = "14",
                city = "medan",
                rating = 4.2,
                address = "blablabla",
                categories = listOf(),
                customerReviews = listOf(),
                menus = Menus(
                    drinks = listOf(),
                    foods = listOf()
                )

            ),
            modifier = Modifier.padding(8.dp),
            onBackClick = {}
        )
    }
}