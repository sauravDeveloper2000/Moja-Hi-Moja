package com.example.mojahimoja.ui.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mojahimoja.repositorySection.accountRepository.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel@Inject constructor(
    private val accountRepository: AccountRepository
): ViewModel() {
    /**
     * SignOutFromApp() - Through this user can sign-out from the app.
     */
    fun signOutFromApp(){
        viewModelScope.launch {
            accountRepository.logOut()
        }
    }
}