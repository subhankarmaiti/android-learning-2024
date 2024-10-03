package com.example.navigationsample

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.navigationsample.ui.theme.NavigationSampleTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationSampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    MyApp()
                }
            }
        }
    }
}


@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "firstScreen") {
        composable("firstScreen") {
            FirstScreen { name,age ->
                navController.navigate("secondScreen/$name/$age")
            }
        }
        composable(route = "secondScreen/{name}/{age}", arguments = listOf(
            navArgument("name") {type = NavType.StringType},
            navArgument("age") { type = NavType.IntType}
        )){
            val name = it.arguments?.getString("name") ?: "No Name"
            val age = it.arguments?.getInt("age") ?: 0

            SecondScreen(name, age) {
                navController.navigate(("thirdScreen"))
            }
        }
        composable(route = "thirdScreen") {
            ThirdScreen {
                navController.navigate("firstSCreen")
            }
        }
    }
}