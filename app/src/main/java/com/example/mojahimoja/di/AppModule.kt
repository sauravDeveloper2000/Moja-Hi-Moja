package com.example.mojahimoja.di

import com.example.mojahimoja.repositorySection.accountRepository.AccountRepository
import com.example.mojahimoja.repositorySection.accountRepository.AccountRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesAccountRepoInstance(
        firebaseAuth: FirebaseAuth
    ): AccountRepository{
        return AccountRepositoryImpl(firebaseAuth = firebaseAuth)
    }
}