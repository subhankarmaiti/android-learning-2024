package com.example.myshopinglistapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import com.example.myshopinglistapp.ui.theme.MyShopingListAppTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            MyShopingListAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize().padding(0.dp, 30.dp, 0.dp, 10.dp)) {
                    Navigation()
                }
            }
        }
    }
}


@Composable
fun Navigation() {
    val navController = rememberNavController()
    val viewmodel: LocationViewModal = viewModel()
    val context = LocalContext.current
    val locationUtils = LocationUtils(context)

    NavHost(navController, startDestination = "shoppingListScreen") {
        composable("shoppingListScreen") {
            ShoppingListApp(
                locationUtils,
                viewmodel,
                navController,
                context,
                address = viewmodel.address.value.firstOrNull()?.formatted_address ?: ""
            )
        }
        dialog("locationScreen") { backStack ->
            viewmodel.location.value?.let { it1 ->
                LocationSelectionScreen(location = it1, onLocationSelected = { locationData ->
                    viewmodel.fetchAddress("${locationData.latitude},${locationData.longitude}")
                    navController.popBackStack()
                })
            }
        }
    }
}

