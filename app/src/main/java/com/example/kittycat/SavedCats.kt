package com.example.kittycat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.kittycat.catapi.data.Cat
import com.example.kittycat.data.Datasource

class SavedCats : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_cats2)
        setContent{
            catList()
        }
    }


    @Composable
    fun catList(modifier: Modifier = Modifier)
    {



    }

    val data: Datasource = Datasource()
    val source = data.getList()

    @Composable
    fun CatList(catList: MutableList<Cat?>, modifier: Modifier = Modifier) {
        LazyColumn(modifier = modifier) {
            catList.forEach{ cat ->
                /*return cat*/
            }
        }

    /*@Composable
    fun catCard(cat: Cat?, modifier: Modifier = Modifier)
    {
        Column {
            Cat?()
        }
    }*/


    /*@Composable
    fun catList()
    {
        catList(modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
        )*/

    }
}