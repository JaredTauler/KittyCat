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
import kotlin.random.Random
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

    init {
        CoroutineScope(Dispatchers.IO).launch {
            updateBible()
        }
        Log.d("fortnite", "hi21")
    }

    private suspend fun updateBible() {
//        try {
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
            val filtered = _bibleData.value.orEmpty().filterIndexed { index, _ -> index !in Seen }
            val chosen = Random.nextInt(filtered.size)
            Seen.add(chosen) // cat has been seen now
            return  filtered[chosen]
        } catch (e: java.lang.IllegalArgumentException) {
            return Cat("1", emptyList(), "none")
        }
    }

    fun readCurrentCat(): Cat? {
        return currentCat.value
    }
}