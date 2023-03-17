package com.example.pizzadelivery

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pizzadelivery.ui.PizzaViewModel

enum class PizzaDeliveryScreen(@StringRes val title : Int) {
    Start(R.string.app_name),
    Menu(R.string.menu_screen),
    Extras(R.string.extras_screen),
    Beverages(R.string.beverages_screen),

}

@Composable
fun PizzaDeliveryApp(
    modifier: Modifier = Modifier,
    pizzaViewModel: PizzaViewModel = viewModel()
) {
    val pizzaUiState = pizzaViewModel.uiState
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = PizzaDeliveryScreen.valueOf(
        backStackEntry?.destination?.route?: PizzaDeliveryScreen.Start.name
    )
    Scaffold(
        topBar = {
            PizzaDeliveryTopBar(
                navigateUp = {navController.navigateUp()},
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null
            )
        }
    ) {innerPadding ->
        NavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            startDestination = PizzaDeliveryScreen.Start.name
        ) {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PizzaDeliveryTopBar(
    modifier: Modifier = Modifier,
    canNavigateBack : Boolean,
    currentScreen : PizzaDeliveryScreen,
    navigateUp : () -> Unit
) {
    TopAppBar(
        title = {
            Text(stringResource(id = currentScreen.title))
        },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null
                    )
                }
            }

        }
    )
}