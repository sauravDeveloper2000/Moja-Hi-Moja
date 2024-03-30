package com.example.mojahimoja.ui.registrationScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mojahimoja.repositorySection.accountRepository.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationScreenViewModel @Inject constructor(
    private val accountRepository: AccountRepository
): ViewModel() {

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
     * createAccount() - Create account.
     */
    fun createAccount(
        inSuccessCase: () -> Unit,
        inFailureCase: (String) -> Unit
    ){
        viewModelScope.launch {
            accountRepository.createUserAccount(
                userEmailId = emailId,
                newPassword = newPassword,
                onSuccess = {
                    inSuccessCase()
                }
                ,
                onFailure = {
                    inFailureCase(it)
                }
            )
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