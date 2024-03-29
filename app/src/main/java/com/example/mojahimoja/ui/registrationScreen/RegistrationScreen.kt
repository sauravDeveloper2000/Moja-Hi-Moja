package com.example.mojahimoja.ui.registrationScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    backToLoginPage: () -> Unit,
    registrationScreenViewModel: RegistrationScreenViewModel = hiltViewModel()
) {
    var isPasswordVisible by remember {
        mutableStateOf<Boolean>(false)
    }

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
                value = registrationScreenViewModel.name,
                onValueChange = {
                    registrationScreenViewModel.updateStates(
                        UserActionsOnRegistrationScreen.OnNameFieldClick(
                            name = it
                        )
                    )
                },
                label = {
                    Text(
                        text = "Name*",
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )
            )
            /**
             * For Email-ID Field
             */
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                value = registrationScreenViewModel.emailId,
                onValueChange = {
                    registrationScreenViewModel.updateStates(
                        UserActionsOnRegistrationScreen.OnEmailIdFieldClick(
                            emailId = it
                        )
                    )
                },
                label = {
                    Text(
                        text = "Email-ID*",
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )
            )
            /**
             * For New Password Field
             */
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                value = registrationScreenViewModel.newPassword,
                onValueChange = {
                    registrationScreenViewModel.updateStates(
                        UserActionsOnRegistrationScreen.OnNewPasswordFieldClick(it)
                    )
                },
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image =
                        if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                    IconButton(
                        onClick = {
                            isPasswordVisible = !isPasswordVisible
                        }
                    ) {
                        Icon(imageVector = image, contentDescription = "Visibility")
                    }
                },
                label = {
                    Text(
                        text = "New Password*",
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Password
                )
            )
            /**
             * For Confirm New Password Field
             */
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                value = registrationScreenViewModel.confirmNewPassword,
                onValueChange = {
                    registrationScreenViewModel.updateStates(
                        UserActionsOnRegistrationScreen.OnConfirmNewPasswordFieldClick(
                            confirmPassword = it
                        )
                    )
                },
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image =
                        if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                    IconButton(
                        onClick = {
                            isPasswordVisible = !isPasswordVisible
                        }
                    ) {
                        Icon(imageVector = image, contentDescription = "Visibility")
                    }
                },
                label = {
                    Text(
                        text = "Confirm Password*",
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                )
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
                    onClick = {
                        backToLoginPage()
                        registrationScreenViewModel.resetStates()
                    }
                ) {
                    Text(text = "Login", style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }
}