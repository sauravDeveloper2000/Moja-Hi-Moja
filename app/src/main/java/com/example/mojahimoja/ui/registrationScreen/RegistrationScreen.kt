package com.example.mojahimoja.ui.registrationScreen

import android.util.Patterns
import android.widget.Toast
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
    val context = LocalContext.current
    var displayPassword by remember {
        mutableStateOf(false)
    }

    var nameFieldError by remember {
        mutableStateOf(false)
    }
    var nameErrorText by remember {
        mutableStateOf<String?>(null)
    }
    var emailIDFieldError by remember {
        mutableStateOf(false)
    }
    var emailIDErrorText by remember {
        mutableStateOf<String?>(null)
    }
    var newPasswordFieldError by remember {
        mutableStateOf(false)
    }
    var newPasswordErrorText by remember {
        mutableStateOf<String?>(null)
    }
    var confirmPasswordFieldError by remember {
        mutableStateOf(false)
    }
    var confirmPasswordErrorText by remember {
        mutableStateOf<String?>(null)
    }

    val regexForName = Regex("^[a-zA-Z][A-Z a-z]{2,49}$")
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
            ExtendedFloatingActionButton(
                onClick = {
                    /**
                     * Name validation
                     */
                    if (registrationScreenViewModel.name.isNotEmpty()) {
                        if (registrationScreenViewModel.name.length in 3..50) {
                            if (registrationScreenViewModel.name.matches(regexForName)) {
                                nameErrorText = null
                                nameFieldError = false
                            } else {
                                nameFieldError = true
                                nameErrorText = "Name should only contains alphabets."
                            }
                        } else {
                            nameFieldError = true
                            nameErrorText = "Name length should be in between 3 and 50"
                        }
                    } else {
                        nameFieldError = true
                        nameErrorText = "Please enter your name."
                    }

                    /**
                     * Email-ID Validation
                     */
                    if (registrationScreenViewModel.emailId.isNotEmpty()) {
                        if (Patterns.EMAIL_ADDRESS.matcher(registrationScreenViewModel.emailId)
                                .matches()
                        ) {
                            emailIDFieldError = false
                            emailIDErrorText = null
                        } else {
                            emailIDFieldError = true
                            emailIDErrorText = "Enter valid email-id"
                        }
                    } else {
                        emailIDErrorText = "Please enter your email-id"
                        emailIDFieldError = true
                    }

                    /**
                     * New and Confirm Password Field Validation
                     */
                    if (registrationScreenViewModel.newPassword.isNotEmpty() && registrationScreenViewModel.confirmNewPassword.isNotEmpty()) {

                        if ((registrationScreenViewModel.newPassword.length in 6..15) && (registrationScreenViewModel.confirmNewPassword.length in 6..15)) {
                            if (registrationScreenViewModel.newPassword == registrationScreenViewModel.confirmNewPassword) {
                                newPasswordFieldError = false
                                newPasswordErrorText = null

                                confirmPasswordFieldError = false
                                confirmPasswordErrorText = null
                            } else {
                                // passwords are matching or not
                                newPasswordFieldError = true
                                newPasswordErrorText = "Passwords are not matching"

                                confirmPasswordFieldError = true
                                confirmPasswordErrorText = "Passwords are not matching"
                            }
                        } else {
                            // Passwords length are 6 or 15 char
                            newPasswordFieldError = registrationScreenViewModel.newPassword.length !in 6..15
                            newPasswordErrorText =
                                if (registrationScreenViewModel.newPassword.length in 6..15) null else "Password length should be 6 to 15 characters"

                            confirmPasswordFieldError =
                                registrationScreenViewModel.confirmNewPassword.length !in 6..15
                            confirmPasswordErrorText =
                                if (registrationScreenViewModel.confirmNewPassword.length in 6..15) null else "Password length should be 6 to 15 characters"

                        }
                    } else {
                        // Passwords are empty or not
                        newPasswordFieldError = registrationScreenViewModel.newPassword.isEmpty()
                        newPasswordErrorText =
                            if (registrationScreenViewModel.newPassword.isEmpty()) "Please enter your new password" else null

                        confirmPasswordFieldError = registrationScreenViewModel.confirmNewPassword.isEmpty()
                        confirmPasswordErrorText =
                            if (registrationScreenViewModel.confirmNewPassword.isEmpty()) "Please enter your confirm password" else null
                    }

                    if (nameFieldError || emailIDFieldError || confirmPasswordFieldError || newPasswordFieldError) {
                        Toast.makeText(context, "Validation Failed", Toast.LENGTH_SHORT).show()
                        return@ExtendedFloatingActionButton
                    } else {
                        Toast.makeText(context, "Validation Succeeded", Toast.LENGTH_SHORT).show()
                        registrationScreenViewModel.createAccount(
                            inSuccessCase = {
                                Toast.makeText(context, "Account Created Successfully", Toast.LENGTH_SHORT).show()
                            },
                            inFailureCase = {
                                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                            }
                        )
                    }
                }
            ) {
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
                ),
                isError = nameFieldError,
                supportingText = {
                    nameErrorText?.let { Text(text = it, fontSize = 10.sp) }
                }
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
                isError = emailIDFieldError,
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
                isError = newPasswordFieldError,
                supportingText = {
                    newPasswordErrorText?.let {
                        Text(text = it, fontSize = 10.sp)
                    }
                },
                visualTransformation = if (displayPassword) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image =
                        if (displayPassword) Icons.Default.Visibility else Icons.Default.VisibilityOff
                    IconButton(
                        onClick = {
                            displayPassword = !displayPassword
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
                isError = confirmPasswordFieldError,
                supportingText = {
                    confirmPasswordErrorText?.let {
                        Text(text = it, fontSize = 10.sp)
                    }
                },
                visualTransformation = if (displayPassword) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image =
                        if (displayPassword) Icons.Default.Visibility else Icons.Default.VisibilityOff
                    IconButton(
                        onClick = {
                            displayPassword = !displayPassword
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