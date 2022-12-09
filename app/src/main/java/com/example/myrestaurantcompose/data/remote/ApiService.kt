package com.example.myrestaurantcompose.data.remote

import com.example.myrestaurantcompose.data.remote.response.ResponseDetailRestaurant
import com.example.myrestaurantcompose.data.remote.response.ResponseRestaurant
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    /**
     * api to handle list restaurant
     * @param
     */
    @GET("/list")
    suspend fun getListRestaurant():ResponseRestaurant

    /**
     * api to handle list restaurant
     */
    @GET("/detail/{id}")
    suspend fun getDetailRestaurant(
        @Path("id") id: String
    ):ResponseDetailRestaurant

    @GET("/search")
    suspend fun searchRestaurant(
        @Query("q") query: String
    ):ResponseRestaurant
}