package com.example.mojahimoja.repositorySection.accountRepository

import com.google.firebase.auth.FirebaseUser

interface AccountRepository {

    /**
     * createUserAccount() - This function we can create new user account on firebase server with its credentials i.e., email and password.
     */
    suspend fun createUserAccount(
        userEmailId: String,
        newPassword: String,
        onSuccess: (FirebaseUser?) -> Unit,
        onFailure: (String) -> Unit
    )

    /**
     * Through this function user can logout from the app.
     */
    suspend fun logOut()
}