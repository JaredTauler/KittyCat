package com.example.kittycat.catapi

import retrofit2.Response
import retrofit2.http.GET

interface Cataas {
    @GET("/quotes")
    suspend fun getQuotes() : Response<CatPicture>
}