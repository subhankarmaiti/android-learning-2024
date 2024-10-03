package com.example.myreceipeapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RecipeApp(navHostController: NavHostController) {
    val recipeViewModal: MainViewModal = viewModel()
    val viewState by recipeViewModal.categoriesState

    NavHost(navController = navHostController, startDestination = Screen.RecipeScreen.route) {
        composable(route = Screen.RecipeScreen.route) {
            RecipeScreen(navigateToDetail = {
                // This part is responsible for passing data from the current screen to the detail screen.
                // It utilizes the savedStateHandle, which is a component of the compose navigation framework
                navHostController.currentBackStackEntry?.savedStateHandle?.set("cat", it)
                navHostController.navigate(Screen.DetailScreen.route)
            },
                viewState = viewState)
        }
        composable(route = Screen.DetailScreen.route) {
            val category = navHostController.previousBackStackEntry?.savedStateHandle?.get<Category>("cat") ?: Category("", "", "", "")
            CategoryDetailScreen(category)
        }
    }
}