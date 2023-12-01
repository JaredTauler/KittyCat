package com.example.kittycat.catapi

import kotlinx.coroutines.*

import android.util.Log
import com.example.kittycat.catapi.data.*
import retrofit2.Response



class CatBible {

    private var apiJob: Job? = null

    // API connector
    private var CataasConn = RetrofitHelper.getInstance().create(Cataas::class.java)

    private var bible: Map<String, Cat>? = null

    public var result: String = ""

    init {
        getDataFromAPI()
    }

    fun getDataFromAPI() {
        apiJob?.cancel()
        apiJob = GlobalScope.launch(Dispatchers.IO)
        {
            //TODO catch errors
//            val c: Response<count> = CataasConn.getCount()

            // get all cats
            val response = CataasConn.getCats(10.toString())

            val bible: Map<String, Cat> = response.body()
                ?.associateBy { it._id }
                ?: emptyMap()

//            Log.d("fortnite ", catMap.toString())
        }


    }
}