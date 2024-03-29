package com.example.mojahimoja.ui.registrationScreen

/**
 * This sealed class "UserActionsOnRegistrationScreen" Handles possible user actions on sign-up screen.
 */
sealed interface UserActionsOnRegistrationScreen {
    data class OnNameFieldClick(val name: String): UserActionsOnRegistrationScreen
    data class OnEmailIdFieldClick(val emailId: String): UserActionsOnRegistrationScreen
    data class OnNewPasswordFieldClick(val newPassword: String): UserActionsOnRegistrationScreen
    data class OnConfirmNewPasswordFieldClick(val confirmPassword: String): UserActionsOnRegistrationScreen
}