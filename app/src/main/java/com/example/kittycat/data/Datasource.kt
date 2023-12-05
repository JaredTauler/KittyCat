package com.example.kittycat.data

import android.media.Image
import com.example.kittycat.R
import com.example.kittycat.model.Saved
import com.example.kittycat.catapi.CatBible
import com.example.kittycat.catapi.data.Cat
import com.example.kittycat.data.Datasource

class Datasource() {

    //creates list to add cats to
    var cats = mutableListOf<Cat?>()

    //returns cat list
    fun getList(): MutableList<Cat?>
    {
        return cats
    }

    //add pic to cat list
    fun addPic(list: MutableList<Cat?>, cat: Cat?){
        list.add(cat)
    }

    //should return list if cats
    fun loadCatPics(): List<Cat>{
        return listOf<Cat>(
            //pictures added as button clicked hopefully?
        )
    }
}