package com.example.gallery

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toFile
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://gallery.prod2.webant.ru/"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val navController = (supportFragmentManager.findFragmentById(R.id.mainFragmentContainer) as NavHostFragment).navController


        val api = (application as MyApp).photoApi

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val photos = api.registerUser(RequestUser("user@example.com",
                    "https://example.com/",
                    "2025-07-11T08:21:30.939Z",
                    "name",
                    listOf("role"),
                    "8888888",
                    "password"
                )
                )
                Log.i("my", photos.email)
            }catch (e: Exception){
                Log.i("my", e.localizedMessage)
            }

        }
    }
}