package ibu.edu.ba.myapplication.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ibu.edu.ba.myapplication.model.User
import ibu.edu.ba.myapplication.ui.viewmodel.UserViewModel

@Composable
fun ProfileScreen(
    userViewModel: UserViewModel,
    onLogoutSuccess: () -> Unit,
    loggedUser: User?
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Profile",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            if (loggedUser != null) {
                Text(text = "Name: ${loggedUser.fullName}", style = MaterialTheme.typography.bodyLarge)
                Text(text = "Email: ${loggedUser.email}", style = MaterialTheme.typography.bodyLarge)
            } else {
                Text(text = "No user logged in", style = MaterialTheme.typography.bodyLarge)
            }

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = {
                    userViewModel.logout()
                    onLogoutSuccess()
                },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) {
                Text(text = "Logout", color = MaterialTheme.colorScheme.onError)
            }
        }
    }
}
