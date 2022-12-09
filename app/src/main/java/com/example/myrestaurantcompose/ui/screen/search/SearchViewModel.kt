package com.example.myrestaurantcompose.ui.screen.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myrestaurantcompose.data.RestaurantRepository
import com.example.myrestaurantcompose.data.remote.response.RestaurantsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.myrestaurantcompose.data.Result

@HiltViewModel
class SearchViewModel @Inject constructor(private val restaurantRepository: RestaurantRepository): ViewModel() {

    private val _restaurant = MutableStateFlow<Result<List<RestaurantsItem>>>(Result.Loading)
    val restaurant  = _restaurant.asStateFlow()

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun searchRestaurant(newQuery: String){
        _query.value = newQuery
        viewModelScope.launch {
            restaurantRepository.searchRestaurant(_query.value).collect() {
                _restaurant.value = it
            }
        }
    }
}