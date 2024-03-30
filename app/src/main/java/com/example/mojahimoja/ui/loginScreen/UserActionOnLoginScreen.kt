package com.example.mojahimoja.ui.loginScreen

sealed interface UserActionOnLoginScreen {
    data class OnEmailIdFieldClick(val emailId: String): UserActionOnLoginScreen
    data class OnPasswordFieldClick(val password: String): UserActionOnLoginScreen
}