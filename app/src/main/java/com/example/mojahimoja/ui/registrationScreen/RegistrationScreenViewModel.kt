package com.example.mojahimoja.ui.registrationScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationScreenViewModel @Inject constructor(): ViewModel() {

    var name by mutableStateOf<String>("")
        private set
    var emailId by mutableStateOf("")
        private set
    var newPassword by mutableStateOf("")
        private set
    var confirmNewPassword by mutableStateOf("")
        private set

    /**
     *  updateStates() - This function updates or initializes the following above states based upon event
     */
    fun updateStates(
        event: UserActionsOnRegistrationScreen
    ){
        when(event){
            is UserActionsOnRegistrationScreen.OnConfirmNewPasswordFieldClick -> this.confirmNewPassword = event.confirmPassword
            is UserActionsOnRegistrationScreen.OnEmailIdFieldClick -> this.emailId = event.emailId
            is UserActionsOnRegistrationScreen.OnNameFieldClick -> this.name = event.name
            is UserActionsOnRegistrationScreen.OnNewPasswordFieldClick -> this.newPassword = event.newPassword
        }
    }

    /**
     * resetStates() - This function reset states when user navigate to login page.
     */
    fun resetStates(){
        name = ""
        emailId = ""
        newPassword = ""
        confirmNewPassword = ""
    }
}