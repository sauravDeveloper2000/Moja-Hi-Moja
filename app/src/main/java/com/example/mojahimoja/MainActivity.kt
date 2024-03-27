package com.example.mojahimoja

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mojahimoja.navigationInfo.AppNavigation
import com.example.mojahimoja.navigationInfo.Destinations
import com.example.mojahimoja.navigationInfo.StartDestinationViewModel
import com.example.mojahimoja.ui.homeScreen.HomeScreen
import com.example.mojahimoja.ui.loginScreen.LoginScreen
import com.example.mojahimoja.ui.registrationScreen.RegistrationScreen
import com.example.mojahimoja.ui.theme.MojaHiMojaTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private val startDestinationViewModel: StartDestinationViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        firebaseAuth = Firebase.auth
        super.onCreate(savedInstanceState)
        setContent {
            MojaHiMojaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val startingDestination by startDestinationViewModel._startingDestination.collectAsState()
                    AppNavigation(startingDestination = startingDestination)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        firebaseAuth.addAuthStateListener { auth ->     // We have attached listener to firebase auth. So when ever their's change
            val currentUser = auth.currentUser          // this block of code get execute.
            if (currentUser != null){
                startDestinationViewModel.setsStartDestination(startDestination = Destinations.HomeScreen)
            } else {
                startDestinationViewModel.setsStartDestination(startDestination = Destinations.PreAuth)
            }
        }
    }
}
