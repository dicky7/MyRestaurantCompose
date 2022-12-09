package com.example.myrestaurantcompose.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetcoffee.ui.components.SectionText
import com.example.myrestaurantcompose.R
import com.example.myrestaurantcompose.data.Result
import com.example.myrestaurantcompose.data.remote.response.RestaurantsItem
import com.example.myrestaurantcompose.ui.components.RestaurantContentItem
import com.example.myrestaurantcompose.ui.components.SearchBar

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit

) {
    val viewModel = hiltViewModel<HomeViewModel>()
    Column(modifier.padding(top = 15.dp)) {
        SectionText(title = stringResource(R.string.near_restaurant))
        HomeContent(
            viewModel = viewModel,
            navigateToDetail = navigateToDetail,
        )
    }


}

@Composable
fun HomeContent(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit
) {
    viewModel.restaurant.collectAsState(initial = Result.Loading).value.let {result->
        when(result){
            is Result.Loading->{
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator()
                }
            }
            is Result.Success->{
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = modifier
                ){
                    items(result.data){data->
                        RestaurantContentItem(
                            restaurants = data,
                            modifier = Modifier.clickable {
                                navigateToDetail(data.id)
                            }
                        )
                    }
                }
            }
            is Result.Error->{
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(result.error)
                }
            }
        }

    }

    
}