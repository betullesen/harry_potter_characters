package com.betulesen.harrypotterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.betulesen.harrypotterapp.ui.theme.HarryPotterAppTheme
import com.betulesen.harrypotterapp.view.CharacterDetailScreen
import com.betulesen.harrypotterapp.view.CharacterListScreen
import com.betulesen.harrypotterapp.view.CharacterSplashScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HarryPotterAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController , startDestination = "splash_screen"){

                    composable("splash_screen"){
                        // splashScreen
                        CharacterSplashScreen(navController = navController)
                    }

                    composable("character_list_screen"){
                        //characterListScreen
                        CharacterListScreen(navController = navController)
                    }

                    composable("character_detail_screen/{characterId}", arguments = listOf(
                        navArgument("characterId"){
                            type = NavType.StringType
                        }
                    )){
                        val characterId = remember {
                            it.arguments?.getString("characterId")
                        }

                        //characterDetailScreen
                        CharacterDetailScreen(
                            id = characterId ?: ""
                            , navController = navController)

                    }
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
    HarryPotterAppTheme {
        Greeting("Android")
    }
}