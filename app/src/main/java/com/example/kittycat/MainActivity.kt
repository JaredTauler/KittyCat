package com.example.kittycat


//import com.example.kittycat.catapi.hold
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.kittycat.catapi.CatBible
import com.example.kittycat.catapi.data.Cat
import com.example.kittycat.ui.theme.KittyCatTheme

class MainActivity : ComponentActivity(), CatBibleCallback {
    private val catBible = CatBible()
    var isBibleLoaded by mutableStateOf(false)
    var currentScreen by mutableStateOf("discoverCat")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        catBible.setCallback(this)

        setContent {
            KittyCatTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "discoverCat") {
                    composable("discoverCat") {
                        if (isBibleLoaded) {
                            DiscoverCat(catBible, onNavigate = {
                                currentScreen = "secondScreen"
                                navController.navigate("secondScreen")
                            })
                        } else {
                            // You can show a loading UI or placeholder here
                            // while waiting for onBibleLoaded callback
                            Text(text = "Loading...")
                        }
                    }
                    composable("secondScreen") {
                        SecondScreen(catBible, navController = navController)
                    }
                }
            }
        }
    }

    override fun onBibleLoaded() {
        Log.d("fortnite ", "bible loaded!")
        isBibleLoaded = true
    }
}


@Composable
fun DiscoverCat(bible: CatBible, modifier: Modifier = Modifier, onNavigate: () -> Unit) {
    var cat by remember { mutableStateOf(bible.currentCat) }

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {


        Log.d("fortnite", cat.toString())
        Row(
            modifier = Modifier
                .height(500.dp)
                .fillMaxWidth()
        ) {
            CatImage(cat = bible.currentCat)
        }

        Button(
            onClick = {
                bible.newCat()
                cat = bible.currentCat // I hate livedata so much.
                Log.d("fortnite", "new cat!")
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Generate New Cat")
        }
        Button(
            onClick = {
                bible.likeCat()

            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Me likey")
        }

        Button(
            onClick = onNavigate,
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Go to Second Screen")
        }

        Log.d("fortnite", bible.currentCat?._id.toString())
    }
}

@Composable
fun SecondScreen(bible: CatBible, navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "List of Liked Cats", fontSize = 24.sp)

        // Display the list of liked cats
        LazyColumn {
            items(bible.likedCats.size) { cat ->
                // Display each cat in the list
                CatItem(cat = bible.likedCats[cat])
            }
        }


    }

    Button(
        onClick = {
            navController.popBackStack()
        },
        modifier = Modifier.padding(8.dp)
    ) {
        Text(text = "Go back to First Screen")
    }
}

@Composable
fun CatImage(cat: Cat?, modifier: Modifier = Modifier) {
    // TODO handle null cat
    if (cat != null) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current).data(
                cat.imgurl()
            )
                .crossfade(true).build(),
//            error = painterResource(R.drawable.ic_broken_image),
//            placeholder = painterResource(R.drawable.loading_img),
            contentDescription = "",
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxSize()
        )
    }

}

@Composable
fun CatItem(cat: Cat) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CatImage(
            cat = cat, modifier = Modifier
                .size(50.dp)
                .clip(shape = RoundedCornerShape(4.dp))
                .background(MaterialTheme.colorScheme.primary)
        )

        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "meow", fontSize = 16.sp)
    }
}