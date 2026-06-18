package com.himanshumehra.kumaonfresh.presentation.ui.registration

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.himanshumehra.kumaonfresh.R
import com.himanshumehra.kumaonfresh.presentation.ui.common.LoadingScreen
import com.himanshumehra.kumaonfresh.presentation.ui.home.HomeScreen

@Composable
fun RegistrationPage(navController: NavController,viewModel: CreateUserViewModel = hiltViewModel()) {
    val uiState = viewModel.uiState.collectAsState().value
    val name = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val phone = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }

    when (uiState) {
        is CreateUserViewModel.UiState.Idle -> {
            RegistrationContent(
                name = name.value,
                email = email.value,
                phone = phone.value,
                password = password.value,
                confirmPassword = confirmPassword.value,
                onNameChange = { name.value = it },
                onEmailChange = { email.value = it },
                onPhoneChange = { phone.value = it },
                onPasswordChange = { password.value = it },
                onConfirmPasswordChange = { confirmPassword.value = it },
                onCreateUser = {
                    viewModel.createUser(
                        name.value,
                        email.value,
                        phone.value,
                        password.value
                    )
                },
                onBackPress = {navController.popBackStack()}
            )
        }

        is CreateUserViewModel.UiState.Loading -> {
            LoadingScreen()
        }

        is CreateUserViewModel.UiState.Success -> {
            HomeScreen(navController = navController)
        }

        is CreateUserViewModel.UiState.Error -> {
            // Handle error state, e.g., show a message
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationContent(
    name: String,
    email: String,
    phone: String,
    password: String,
    confirmPassword: String,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPhoneChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onCreateUser: () -> Unit,
    onBackPress:()->Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(text = "Registration") },
                    navigationIcon = {
                        IconButton(onClick = {onBackPress()}) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.Red,
                        titleContentColor = Color.White,
                        navigationIconContentColor = Color.White
                    )
                )
            }
        ) { padding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .padding(padding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(R.drawable.ic_launcher_background),
                    contentDescription = "Registration Image"
                )

                Spacer(modifier = Modifier.height(30.dp))

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = name,
                    onValueChange = { onNameChange(it) },
                    label = { Text(text = "Name") },
                    shape = RoundedCornerShape(8.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = email,
                    onValueChange = { onEmailChange(it) },
                    label = { Text(text = "Email") },
                    shape = RoundedCornerShape(8.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = phone,
                    onValueChange = { onPhoneChange(it) },
                    label = { Text(text = "Phone") },
                    shape = RoundedCornerShape(8.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = password,
                    onValueChange = { onPasswordChange(it) },
                    label = { Text(text = "Password") },
                    shape = RoundedCornerShape(8.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = confirmPassword,
                    onValueChange = { onConfirmPasswordChange(it) },
                    label = { Text(text = "Confirm Password") },
                    shape = RoundedCornerShape(8.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.height(40.dp))

                Button(modifier = Modifier.fillMaxWidth(), onClick = { onCreateUser() }) {
                    Text(
                        text = "Register",
                    )
                }


            }

        }

    }
}

@Preview
@Composable
fun PreviewRegistrationContent() {
    val name = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val phone = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    RegistrationContent(
        name = name.value,
        email = email.value,
        phone = phone.value,
        password = password.value,
        confirmPassword = confirmPassword.value,
        onNameChange = { name.value = it },
        onEmailChange = { email.value = it },
        onPhoneChange = { phone.value = it },
        onPasswordChange = { password.value = it },
        onConfirmPasswordChange = { confirmPassword.value = it },
        onCreateUser = {},
        onBackPress = {}
    )
}