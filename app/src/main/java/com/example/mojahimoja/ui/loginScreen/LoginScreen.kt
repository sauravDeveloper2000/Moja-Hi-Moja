package com.example.mojahimoja.ui.loginScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navigateToRegistrationScreen:() -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Login", style = MaterialTheme.typography.titleLarge) })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier.padding(10.dp)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 5.dp),
                    text = "Enter your Info.",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 18.sp
                )
                /**
                 * For Email-ID Field
                 */
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    value = "",
                    onValueChange = {},
                    label = {
                        Text(
                            text = "Email-ID*",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                )
                /**
                 * For Password Field
                 */
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    value = "",
                    onValueChange = {},
                    label = {
                        Text(
                            text = "Password*",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                )
                /**
                 * If user don't have an account, then take it to registration screen
                 */
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Don't have an account!",
                        style = MaterialTheme.typography.titleMedium
                    )
                    TextButton(
                        onClick = navigateToRegistrationScreen,
                        shape = RoundedCornerShape(10.dp),
                    ) {
                        Text(
                            text = "Create",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }
            /**
             * Login Button
             */
            Button(
                shape = RoundedCornerShape(10.dp),
                onClick = { /*TODO*/ }
            ) {
                Text(text = "Login")
            }
        }
    }
}