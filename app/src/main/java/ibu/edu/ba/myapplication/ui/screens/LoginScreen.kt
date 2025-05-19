package ibu.edu.ba.myapplication.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ibu.edu.ba.myapplication.R
import ibu.edu.ba.myapplication.model.User
import ibu.edu.ba.myapplication.ui.viewmodel.UserViewModel

@Composable
fun LoginScreen(userViewModel: UserViewModel, onRegisterNav: () -> Unit, onSuccessLogin: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val loginStatus by userViewModel.loginStatus.collectAsState()

    LaunchedEffect(loginStatus) {
        loginStatus?.let { success ->
            if (success) {
                onSuccessLogin()
            } else {
                //do something else
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize().background(Color(0xFF1B1B1B)),
        contentAlignment = Alignment.BottomCenter
    ){
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.minimalist_night_rocket),
            contentDescription = "Login background",
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 30.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { it -> email = it },
                label = { Text("Email", color = Color.White) },
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = null, tint = Color.White) } ,
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedTextColor = Color.White,
                    focusedTextColor = Color.White,
                    focusedIndicatorColor = Color.White
                )
            )

            //Spacer(modifier = Modifier.height(1.dp))
            OutlinedTextField(
                value = password,
                onValueChange = {it -> password = it},
                label = { Text("Password", color = Color.White) },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = Color.White) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                )
            )

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    userViewModel.login(email, password)
                },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.hsl(
                    hue = 187.88f,       // Hue in degrees
                    saturation = 1f,     // 100% = 1f
                    lightness = 0.3137f  // 31.37% = 0.3137f
                ))
            ) {
                Text(text = "Log In")
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(modifier = Modifier.weight(1f), color = Color.White)
                Text(text = "or", color = Color.White, modifier = Modifier.padding(8.dp))
                HorizontalDivider(modifier = Modifier.weight(1f), color = Color.White)
            }
            //Can be done with OutlinedButton component also
            Button(
                onClick = {onRegisterNav()},
                modifier = Modifier.fillMaxWidth().
                border(width = 2.dp, color = Color.Gray, shape = RoundedCornerShape(10.dp)),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
            ) {
                Text(text = "Sign up", color = Color.LightGray)
            }
        }
    }
}