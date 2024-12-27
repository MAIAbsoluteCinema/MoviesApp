package com.example.movies.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.movies.viewmodel.UserViewModel


@Composable
fun LoginScreen(navController: NavController, userViewModel: UserViewModel = viewModel()) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(value = email,
            onValueChange = { email = it },
            label = { Text("Электронная почта") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(value = password,
            onValueChange = { password = it },
            label = { Text("Пароль") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val isSuccess = userViewModel.loginUser(email, password)
                if (isSuccess) {
                    navController.navigate("main")
                } else {
                    // Показать ошибку
                }
            }, modifier = Modifier.fillMaxWidth()
        ) {
            Text("Войти")
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = {
            navController.navigate("register")
        }) {
            Text("Нет учетной записи? Зарегистрироваться")
        }
    }
}
