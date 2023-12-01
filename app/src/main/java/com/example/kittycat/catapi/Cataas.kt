package com.example.kittycat.catapi

import com.example.kittycat.catapi.data.Cat
import com.example.kittycat.catapi.data.count

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

data class ApiResponse<T>(val data: T)

interface Cataas {
    @GET("/cat?json=true")
    suspend fun getCat() : Response<Cat>

//    @GET("/api/cats")
//    suspend fun getCats(@Query("limit") limit: String) : Response<Map<String, Cat>>

    @GET("/api/cats")
    suspend fun getCats(
        @Query("limit") limit: String
    ): Response<List<Cat>>


    @GET("/api/count")
    suspend fun getCount() : Response<count>
}