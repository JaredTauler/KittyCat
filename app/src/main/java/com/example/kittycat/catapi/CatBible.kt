package com.example.kittycat.catapi

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.kittycat.CatBibleCallback
import com.example.kittycat.catapi.data.Cat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
class CatBible : ViewModel() {

    private var apiJob: Job? = null

    // API connector
    private var CataasConn = RetrofitHelper.getInstance().create(Cataas::class.java)

//    private var _bibleData = <List<Cat>>()
    var bibleData: MutableList<Cat> = mutableListOf()


    private var Seen: MutableList<Int> = mutableListOf()

    public var result: String = ""

    private var callback: CatBibleCallback? = null

    var currentCat: Cat? = null
    var likedCats: MutableList<Cat> = mutableListOf()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            updateBible()

        }
    }

    private suspend fun updateBible() {
//        try {
            Thread.sleep(2_000)
            withContext(Dispatchers.Main) {
                bibleData = getDataFromAPI()
                newCat()
                notifyCallback()
            }

//        } catch (e: Exception) {
            // Handle the error
//        }
    }

    private fun notifyCallback() {
        Log.d("fortnite ", "HELL")
        callback?.onBibleLoaded()
    }

    fun setCallback(catBibleCallback: CatBibleCallback) {
        callback = catBibleCallback
    }

    private suspend fun getDataFromAPI(): MutableList<Cat> {

        val response = CataasConn.getCats(10.toString()).body()
        return response ?: mutableListOf() // TODO Handle the case when the response is null
    }



    fun newCat() {
//        CoroutineScope(Dispatchers.IO).launch {
        currentCat = findCat()
//        }
    }

    fun findCat(): Cat {
        try {
            val filtered = bibleData

            // Create a set of all indices
            val allIndices = filtered.indices.toMutableSet()

            // Remove seen indices
            allIndices.removeAll(Seen)

            // Choose a random index from the remaining set
            val chosen = allIndices.random()

            // Add the chosen index to the seen set
            Seen.add(chosen)

            return  filtered[chosen]

        } catch (e: java.lang.IllegalArgumentException) {
            // TODO error cat
            return Cat("1", emptyList(), "none")
        }
    }

    fun likeCat() {
//        val currentLikedCats = likedCats.orEmpty().toMutableList()

        val c = currentCat
        if (
            c != null && !likedCats.contains(c)
        ) {
            likedCats.add(c)
        }
        Log.d("fortnite", likedCats.toString())
    }

//    fun readCurrentCat(): Cat? {
//        return currentCat
//    }
}