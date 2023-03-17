package com.example.pizzadelivery.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PizzaViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(PizzaUiState())
    val uiState : StateFlow<PizzaUiState> = _uiState.asStateFlow()


}