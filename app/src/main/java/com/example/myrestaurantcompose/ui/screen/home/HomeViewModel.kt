package com.example.myrestaurantcompose.ui.screen.home

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
class HomeViewModel @Inject constructor(private val restaurantRepository: RestaurantRepository): ViewModel() {

    private val _restaurant = MutableStateFlow<Result<List<RestaurantsItem>>>(Result.Loading)
    val restaurant  = _restaurant.asStateFlow()
    init {
        searchRestaurant("\"")
    }

    fun searchRestaurant(query: String){
        _restaurant.value = Result.Loading
        viewModelScope.launch {
            restaurantRepository.searchRestaurant(query).collect() {
                _restaurant.value = it
            }
        }
    }
}