package com.example.mojahimoja.ui.loginScreen

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
class LoginScreenViewModel @Inject constructor(
    private val accountRepository: AccountRepository
): ViewModel() {

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

    fun signIn(
        inSuccessCase: () -> Unit,
        inFailureCase: (String) -> Unit
    ){
        viewModelScope.launch{
            accountRepository.login(
                email = emailId,
                password = password,
                onSuccess = {
                    inSuccessCase
                },
                onFailure = {
                    inFailureCase(it)
                }
            )
        }
    }
}