package com.lucky.ecommmerce

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.lucky.ecommmerce.data.SharedPreferencesManager
import com.lucky.ecommmerce.ui.navigation.NavGraph
import com.lucky.ecommmerce.ui.theme.EcommmerceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcommmerceTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    val sharedPreferencesManager = SharedPreferencesManager(this)

                    // Define the start destination based on whether the user is logged in
                    val startDestination = if (sharedPreferencesManager.getToken() != null) {
                        "home"
                    } else {
                        "login"
                    }

                    NavGraph(navController = navController, context = this, startDestination = startDestination)
                }
            }
        }
    }
}
