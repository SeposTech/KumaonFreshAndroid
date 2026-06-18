package com.himanshumehra.kumaonfresh.presentation.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.himanshumehra.kumaonfresh.R
import com.himanshumehra.kumaonfresh.presentation.ui.common.LoadingScreen
import com.himanshumehra.kumaonfresh.presentation.ui.home.HomeScreen

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = hiltViewModel()) {
    val email = remember { mutableStateOf("nitinrawat2006@gmail.com") }
    val password = remember { mutableStateOf("admin123@") }
    val uiState = viewModel.uiState.collectAsState().value

    when (uiState) {
        is LoginViewModel.UiState.Idle -> {
            LoginContent(
                email.value,
                password.value,
                onEmailChange = { email.value = it },
                onPasswordChange = { password.value = it },
                onLoginClick = { viewModel.login(email.value, password.value) },
                onClickSignUp = { navController.navigate("registration") })

        }

        is LoginViewModel.UiState.Loading -> {
            LoadingScreen()
        }

        is LoginViewModel.UiState.Success -> {
            HomeScreen(navController = navController)
        }

        is LoginViewModel.UiState.Error -> {
            // Handle error state, e.g., show a toast or an error message
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginContent(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onClickSignUp: () -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Login") },
                    navigationIcon = {
                        IconButton(onClick = {}) {
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
                    .padding(padding)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    modifier = Modifier
                        .padding(bottom = 40.dp)
                        .size(100.dp),
                    painter = painterResource(R.drawable.ic_launcher_background),
                    contentDescription = "Login Image"
                )

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = email,
                    onValueChange = { onEmailChange(it) },
                    label = { Text(text = "Email") },
                    shape = RoundedCornerShape(8.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = password,
                    onValueChange = { onPasswordChange(it) },
                    label = { Text(text = "Password") },
                    shape = RoundedCornerShape(8.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.height(40.dp))


                Button(modifier = Modifier.fillMaxWidth(), onClick = { onLoginClick() }) {
                    Text(
                        text = "Login",
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Text(
                    text = "Don't have an account? Sign Up",
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .clickable { onClickSignUp() },
                    color = Color.Blue
                )


            }

        }


    }
}


@Preview
@Composable
fun PreviewLoginContent() {
    val email = remember { mutableStateOf("nitinrawat2006@gmail.com") }
    val password = remember { mutableStateOf("admin123@") }
    LoginContent(
        email = email.value,
        password = password.value,
        onEmailChange = { email.value = it },
        onPasswordChange = { password.value = it },
        onLoginClick = {},
        onClickSignUp = {}
    )
}



