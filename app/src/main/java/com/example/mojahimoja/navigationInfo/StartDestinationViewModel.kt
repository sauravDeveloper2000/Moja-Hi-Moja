package com.example.mojahimoja.navigationInfo

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class StartDestinationViewModel @Inject constructor(): ViewModel() {
    var _startingDestination = MutableStateFlow<Destinations>(Destinations.PreAuth)
        private set

    // setsStartDestination - It assigns the start destination value to _startDestination property.
    fun setsStartDestination(
        startDestination: Destinations
    ){
        _startingDestination.value = startDestination
    }
}