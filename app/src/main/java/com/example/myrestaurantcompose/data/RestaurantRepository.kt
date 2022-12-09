package com.example.myrestaurantcompose.data


import com.example.myrestaurantcompose.data.remote.ApiService
import com.example.myrestaurantcompose.data.remote.response.Restaurant
import com.example.myrestaurantcompose.data.remote.response.RestaurantsItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestaurantRepository @Inject constructor(private val apiService: ApiService) {

    fun getListRestaurant(): Flow<Result<List<RestaurantsItem>>> = flow {
        emit(Result.Loading)
        try {
            val user = apiService.getListRestaurant().restaurants
            emit(Result.Success(user))
        }catch (e: Exception){
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getDetailRestaurant(query: String): Flow<Result<Restaurant>> = flow {
        emit(Result.Loading)
        try {
            val user = apiService.getDetailRestaurant(query).restaurant
            emit(Result.Success(user))
        }catch (e: Exception){
            emit(Result.Error(e.message.toString()))
        }
    }

    fun searchRestaurant(query: String): Flow<com.example.myrestaurantcompose.data.Result<List<RestaurantsItem>>> = flow {
        emit(Result.Loading)
        try {
            val user = apiService.searchRestaurant(query).restaurants
            emit(Result.Success(user))
        }catch (e: Exception){
            emit(Result.Error(e.message.toString()))
        }
    }
}