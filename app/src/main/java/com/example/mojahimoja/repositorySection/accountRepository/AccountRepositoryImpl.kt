package com.example.mojahimoja.repositorySection.accountRepository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

/**
 * This class is the which actually creates new user account on firebase server by implementing this interface.
 */
class AccountRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): AccountRepository {
    override suspend fun createUserAccount(
        userEmailId: String,
        newPassword: String,
        onSuccess: (FirebaseUser?) -> Unit,
        onFailure: (String) -> Unit
    ) {
        firebaseAuth.createUserWithEmailAndPassword(userEmailId, newPassword)       // So we have created new account using this auths method.
            .addOnCompleteListener {
                if (it.isSuccessful){
                    val firebaseUser = it.result.user
                    onSuccess(firebaseUser)
                } else {
                    onFailure(it.exception.toString())
                }
            }
    }

    override suspend fun logOut() {
        firebaseAuth.signOut()
    }

    override fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    onSuccess
                } else {
                    onFailure(it.exception.toString())
                }
            }
    }
}