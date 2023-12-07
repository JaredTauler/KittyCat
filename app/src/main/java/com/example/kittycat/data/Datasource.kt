package com.example.kittycat.data

import android.media.Image
import com.example.kittycat.R
import com.example.kittycat.model.Saved
import com.example.kittycat.catapi.CatBible
import com.example.kittycat.catapi.data.Cat
import com.example.kittycat.data.Datasource

class Datasource() {

    var cats = mutableListOf<String>()
    fun getList(): MutableList<String>
    {
        return cats
    }
    fun addDescription(list: MutableList<String>, cat: String){
        list.add(cat)
    }
    fun loadCatPics(): List<String>{
        return listOf<String>(
            //descriptions added as button clicked hopefully?
        )
    }

    //instead of returning saved cats, it will return user entered descriptions of the cats they saw

    /* scrapped
    //creates list to add cats to
    var cats = mutableListOf<Cat?>()

    //returns cat list
    fun getList(): MutableList<Cat?>
    {
        return cats
    }

    //add pic to cat list, scrapped
    fun addPic(list: MutableList<Cat?>, cat: Cat?){
        list.add(cat)
    }

    //should return list if cats
    fun loadCatPics(): List<Cat>{
        return listOf<Cat>(
            //pictures added as button clicked hopefully?
        )
    }
    */
}