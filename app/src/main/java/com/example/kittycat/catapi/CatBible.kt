package com.example.kittycat.catapi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private var _bibleData = MutableLiveData<List<Cat>>()
    val bibleData: LiveData<List<Cat>> get() = _bibleData


    private var Seen: MutableList<Int> = mutableListOf()

    public var result: String = ""

    private var callback: CatBibleCallback? = null

    var currentCat = MutableLiveData<Cat>()
    var likedCats: MutableList<Cat> = mutableListOf()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            updateBible()

        }
    }

    private suspend fun updateBible() {
//        try {
            Thread.sleep(2_000)
            val newData = getDataFromAPI()
            withContext(Dispatchers.Main) {
                _bibleData.postValue(newData)
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

    private suspend fun getDataFromAPI(): List<Cat> {

        val response = CataasConn.getCats(10.toString()).body()
        return response ?: emptyList() // TODO Handle the case when the response is null
    }



    fun newCat() : Cat? {
        CoroutineScope(Dispatchers.IO).launch {
            currentCat.postValue(findCat())
        }
//        withContext(Dispatchers.Main) {
//            currentCat.postValue(getNewCat())
//        }
        return readCurrentCat()
    }

    fun findCat(): Cat {
        try {
//            val filtered = _bibleData.value.orEmpty().filterIndexed { index, _ -> index !in Seen }
//
//            val chosen = Random.nextInt(filtered.size)
//            Seen.add(chosen) // cat has been seen now
//            Log.d("fortnite", Seen.toString())

            val filtered = _bibleData.value.orEmpty()

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

        val c = currentCat.value
        if (
            c != null && !likedCats.contains(c)
        ) {
            likedCats.add(c)
        }
        Log.d("fortnite", likedCats.toString())
    }

    fun readCurrentCat(): Cat? {
        return currentCat.value
    }
}