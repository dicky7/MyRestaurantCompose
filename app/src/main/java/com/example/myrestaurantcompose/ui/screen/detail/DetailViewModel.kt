package com.example.myrestaurantcompose.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myrestaurantcompose.data.RestaurantRepository
import com.example.myrestaurantcompose.data.Result
import com.example.myrestaurantcompose.data.remote.response.Restaurant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val restaurantRepository: RestaurantRepository): ViewModel() {
    private val _restaurantDetail = MutableStateFlow<Result<Restaurant>>(Result.Loading)
    val restaurantDetail  = _restaurantDetail.asStateFlow()


    fun getRestaurantDetail(query: String){
        _restaurantDetail.value = Result.Loading
        viewModelScope.launch {
            restaurantRepository.getDetailRestaurant(query).collect{
                _restaurantDetail.value = it
            }
        }
    }
}