package com.example.kittycat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.kittycat.catapi.RetrofitHelper
import com.example.kittycat.ui.theme.KittyCatTheme

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
//import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.example.kittycat.catapi.Cataas
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.create

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("fortnite", "HI")
        print("hi")
//
        val CataasConn = RetrofitHelper.getInstance().create(Cataas::class.java)
//        // launching a new coroutine
        GlobalScope.launch {
            val result = CataasConn.getQuotes()
            if (result != null)
            // Checking the results
                Log.d("fortnite ", result.body().toString())
        }

        setContent {
            KittyCatTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }


    }
}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KittyCatTheme {
        Greeting("Android")
    }
}