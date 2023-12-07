package com.example.kittycat


//import com.example.kittycat.catapi.hold
import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField

//problem with navigation import statement????
/*import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController*/

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

enum class CatScreen()
{
    Start,
    Saved
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscoverCat(bible: CatBible, modifier: Modifier = Modifier) {
//    await bible.getnewCat()

    //was trying to make nav controller from cupcake assignment but the
    // imports arent working and I can't test things out

    /*navController: NavHostController = rememberNavController()
    //Navigation Host
    Scaffold(

    ){  innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = CatScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        )
        {
            composable(route = CatScreen.Saved.name){
                MainActivity(
                    cats = DataSource.cats,
                    onNextButtonClicked = {
                        viewModel.setQuantity(it)
                        navController.navigate(CatScreen.Saved.name)
                    }
                )
            }
        }
    }*/

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
//        Text(text = doubles, fontSize = 24.sp)
//        Log.d("fortnite", bible.getnewCat().toString())
//        bible.getnewCat()
        //val navController = rememberNavController()
        val img = "https://cataas.com/cat/BgStpOSAyjeFKwRG"
        var catDescription by remember{
            mutableStateOf("")
        }
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
            modifier = modifier.padding(15.dp)
        ) {
            Text(text = "Click For a Cat", fontSize = 20.sp)

        }

        //code got from: https://developer.android.com/jetpack/compose/text/user-input
        TextField(
            value = catDescription,
            onValueChange = {catDescription = it},
            label = {Text("Cat Description")},
            modifier = modifier.padding(15.dp)
        )


        //changing instead of saving cats, we'll save user descriptions of cats
        /*
        //gets datasource of arrayList of cats
        val data: Datasource = Datasource()
        //gets list of cats in datasource
        val source = data.getList()
        */

        //gets datasource of arrayList of catDescriptions
        val data: Datasource = Datasource()
        //gets list of catsDescriptions in datasource
        val source = data.getList()

        Button(
            //adds picture to cat arrayList in datasource file
            onClick = {
                data.addDescription(source, catDescription)
            },
            modifier = modifier.padding(15.dp)
        ){
            Text(text = "Save Cat Description", fontSize = 20.sp)
        }

        Button(
            onClick = {
                //Intent intent = new Intent(MainActivity.this, SavedCats.class)
            },
            modifier = modifier.padding(bottom = 15.dp)
        ){
            Text(text = "See Saved Cat Descriptions", fontSize = 20.sp)
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