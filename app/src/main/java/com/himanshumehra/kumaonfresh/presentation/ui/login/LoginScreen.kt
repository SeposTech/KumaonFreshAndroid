package com.himanshumehra.kumaonfresh.presentation.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
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
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    when {
        uiState.isLoading -> LoadingScreen()

        uiState.success != null -> HomeScreen(navController = navController)

        else -> {
            LoginContent(
                email = uiState.email,
                password = uiState.password,
                onEmailChange = viewModel::onEmailChange,
                onPasswordChange = viewModel::onPasswordChange,
                onLoginClick = viewModel::login,
                onClickSignUp = { navController.navigate("registration") },
                onBackClick = { navController.popBackStack() },
                errorMessage = uiState.error
            )
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
    onClickSignUp: () -> Unit,
    onBackClick: () -> Unit,
    errorMessage: String? = null
) {
    val scrollState = rememberScrollState()

    Surface(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFFFF3E0),
                            Color(0xFFFFE0B2),
                            Color(0xFFFFF8F1)
                        )
                    )
                )
        ) {
            Scaffold(
                containerColor = Color.Transparent,
                topBar = {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(
                                text = "Login",
                                fontWeight = FontWeight.Bold
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = onBackClick) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Back"
                                )
                            }
                        },
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = Color.Transparent,
                            titleContentColor = Color(0xFF1F1F1F),
                            navigationIconContentColor = Color(0xFF1F1F1F)
                        )
                    )
                }
            ) { padding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(horizontal = 20.dp)
                        .verticalScroll(scrollState),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(12.dp))

                    Box(
                        modifier = Modifier
                            .size(96.dp)
                            .clip(CircleShape)
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            modifier = Modifier.size(70.dp),
                            painter = painterResource(R.drawable.ic_launcher_background),
                            contentDescription = "Login Image"
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Welcome Back",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1F1F1F)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Sign in to continue to your account",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF666666)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(24.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp)
                        ) {
                            Text(
                                text = "Email",
                                fontWeight = FontWeight.Medium,
                                color = Color(0xFF333333)
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = email,
                                onValueChange = onEmailChange,
                                placeholder = { Text("Enter your email") },
                                singleLine = true,
                                shape = RoundedCornerShape(16.dp),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            Text(
                                text = "Password",
                                fontWeight = FontWeight.Medium,
                                color = Color(0xFF333333)
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = password,
                                onValueChange = onPasswordChange,
                                placeholder = { Text("Enter your password") },
                                singleLine = true,
                                shape = RoundedCornerShape(16.dp),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                                visualTransformation = PasswordVisualTransformation()
                            )

                            if (!errorMessage.isNullOrBlank()) {
                                Spacer(modifier = Modifier.height(12.dp))
                                Text(
                                    text = errorMessage,
                                    color = Color.Red,
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }

                            Spacer(modifier = Modifier.height(24.dp))

                            Button(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(54.dp),
                                onClick = onLoginClick,
                                shape = RoundedCornerShape(16.dp)
                            ) {
                                Text(
                                    text = "Login",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            TextButton(
                                modifier = Modifier.fillMaxWidth(),
                                onClick = onClickSignUp
                            ) {
                                Text(
                                    text = "Don't have an account? Sign Up",
                                    color = Color(0xFFEF6C00),
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginContent() {
    LoginContent(
        email = "test@example.com",
        password = "password123",
        onEmailChange = {},
        onPasswordChange = {},
        onLoginClick = {},
        onClickSignUp = {},
        onBackClick = {},
        errorMessage = "Invalid credentials"
    )
}