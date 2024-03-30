package com.example.mojahimoja.ui.loginScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(): ViewModel() {

    var emailId by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set

    fun updatesState(
        event: UserActionOnLoginScreen
    ){
        when(event){
            is UserActionOnLoginScreen.OnEmailIdFieldClick -> {
                this.emailId = event.emailId
            }
            is UserActionOnLoginScreen.OnPasswordFieldClick -> {
                this.password = event.password
            }
        }
    }


}