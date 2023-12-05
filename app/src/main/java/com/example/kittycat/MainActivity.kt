package com.example.kittycat


//import com.example.kittycat.catapi.hold
import android.media.Image
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.kittycat.catapi.CatBible
import com.example.kittycat.catapi.data.Cat
import com.example.kittycat.ui.theme.KittyCatTheme
import com.example.kittycat.data.Datasource
import kotlin.reflect.KProperty1

class MainActivity : ComponentActivity(), CatBibleCallback {
    private val catBible = CatBible()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        catBible.setCallback(this)


        setContent {
            KittyCatTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DiscoverCat(catBible)
                }
            }
        }

    }

    private fun updateCurrentCat() {

    }

    override fun onBibleLoaded() {
        Log.d("fortnite ", "HELL")
    }
}



@Composable
fun DiscoverCat(bible: CatBible, modifier: Modifier = Modifier) {
//    await bible.getnewCat()

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
//        Text(text = doubles, fontSize = 24.sp)
//        Log.d("fortnite", bible.getnewCat().toString())
//        bible.getnewCat()
        val img = "https://cataas.com/cat/BgStpOSAyjeFKwRG"
        var cat by remember { mutableStateOf(
            bible.newCat()
        ) }
        Log.d("fortnite", cat.toString())
        Row(
            modifier = Modifier
                .height(500.dp)
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current).data(
                    bible.readCurrentCat()?.imgurl()
                )
                    .crossfade(true).build(),
//            error = painterResource(R.drawable.ic_broken_image),
//            placeholder = painterResource(R.drawable.loading_img),
                contentDescription = "",
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize()
            )
        }

        Button(
            onClick = {
                cat = bible.newCat()
            },
        ) {
            Text(text = "Click For a Cat", fontSize = 24.sp)
        }

        //gets datasource of arrayList if cats
        val data: Datasource = Datasource()

        //gets list of cats in datasource
        val source = data.getList()


        Button(
            //adds picture to cat arrayList in datasource file
            onClick = {
                data.addPic(source, cat)
            }
        ){
            Text(text = "Save Cat", fontSize = 24.sp)
        }

        Button(
            onClick = {

            }
        ){
            Text(text = "See Saved Cats", fontSize = 24.sp)
        }


        Log.d("fortnite", bible.readCurrentCat()?._id.toString())
    }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    KittyCatTheme {
//        Greeting("Android")
//    }
//}