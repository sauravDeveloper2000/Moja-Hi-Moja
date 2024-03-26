package com.example.mojahimoja.ui.registrationScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mojahimoja.components.HorizontalSpace


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Register",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = { /*TODO*/ }) {
                Text(
                    text = "Create",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    ) { innerPadding ->

        val context = LocalContext.current

        Card(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 5.dp, vertical = 5.dp),
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                text = "Enter your Personal Info.",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
                fontSize = 18.sp
            )
            /**
             * For Name Field
             */
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                value = "",
                onValueChange = {},
                label = {
                    Text(
                        text = "Name*",
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                maxLines = 1,
            )
            /**
             * For Email-ID Field
             */
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 5.dp),
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
             * For New Password Field
             */
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                value = "",
                onValueChange = {},
                label = {
                    Text(
                        text = "New Password*",
                        style = MaterialTheme.typography.bodyMedium
                    )
                })
            /**
             * For Confirm New Password Field
             */
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                value = "",
                onValueChange = {},
                label = {
                    Text(
                        text = "Confirm Password*",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Already have an Account!",
                    style = MaterialTheme.typography.titleMedium
                )
                TextButton(
                    shape = RoundedCornerShape(10),
                    onClick = { /*TODO*/ }
                ) {
                    Text(text = "Login",style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }
}