package com.example.mojahimoja.ui.loginScreen

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
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
fun LoginScreen(
    navigateToRegistrationScreen: () -> Unit,
    loginScreenViewModel: LoginScreenViewModel = hiltViewModel()
) {
    var showPassword by remember {
        mutableStateOf(false)
    }
    var emailIdFieldError by remember {
        mutableStateOf(false)
    }
    var emailIDErrorText by remember {
        mutableStateOf<String?>(null)
    }

    var passwordFieldError by remember {
        mutableStateOf(false)
    }
    var passwordErrorText by remember {
        mutableStateOf<String?>(null)
    }
    val context = LocalContext.current
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
                    value = loginScreenViewModel.emailId,
                    onValueChange = {
                        loginScreenViewModel.updatesState(
                            UserActionOnLoginScreen.OnEmailIdFieldClick(
                                emailId = it
                            )
                        )
                    },
                    isError = emailIdFieldError,
                    supportingText = {
                        emailIDErrorText?.let {
                            Text(text = it, fontSize = 10.sp)
                        }
                    },
                    label = {
                        Text(
                            text = "Email-ID*",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    )
                )
                /**
                 * For Password Field
                 */
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    value = loginScreenViewModel.password,
                    onValueChange = {
                        loginScreenViewModel.updatesState(
                            UserActionOnLoginScreen.OnPasswordFieldClick(
                                password = it
                            )
                        )
                    },
                    isError = passwordFieldError,
                    supportingText = {
                        passwordErrorText?.let {
                            Text(text = it, fontSize = 10.sp)
                        }
                    },
                    label = {
                        Text(
                            text = "Password*",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    },
                    visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val image =
                            if (showPassword) Icons.Default.Visibility else Icons.Default.VisibilityOff
                        IconButton(onClick = {
                            showPassword = !showPassword
                        }) {
                            Icon(imageVector = image, contentDescription = null)
                        }
                    },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    )
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
                onClick = {
                    /**
                     * First email validation
                     */
                    if (loginScreenViewModel.emailId.isNotEmpty()) {
                        if (Patterns.EMAIL_ADDRESS.matcher(loginScreenViewModel.emailId)
                                .matches()
                        ) {
                            emailIdFieldError = false
                            emailIDErrorText = null
                        } else {
                            emailIdFieldError = true
                            emailIDErrorText = "Please enter valid email-id."
                        }
                    } else {
                        emailIdFieldError = true
                        emailIDErrorText = "Please enter your email-id"
                    }

                    /**
                     * Password Validation
                     */
                    if (loginScreenViewModel.password.isNotEmpty()) {
                        if (loginScreenViewModel.password.length in 6..15) {
                            passwordFieldError = false
                            passwordErrorText = null
                        } else {
                            passwordFieldError = true
                            passwordErrorText = "Password Length should be in between 6 and 15."
                        }
                    } else {
                        passwordFieldError = true
                        passwordErrorText = "Please enter your password"
                    }

                    if (emailIdFieldError || passwordFieldError) {
                        Toast.makeText(context, "Validation Failed", Toast.LENGTH_SHORT).show()
                        return@Button
                    } else {
                        Toast.makeText(context, "Validation Succeeded", Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Text(text = "Login")
            }
        }
    }
}