package ibu.edu.ba.myapplication.ui.screens.navigations

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import ibu.edu.ba.myapplication.model.User
import kotlin.math.log

@Composable
fun BottomNavigationBar(loggedUser: User, onWorkoutNav: (User) -> Unit, onHomeNav: (User) -> Unit) {
    var selectedItem by remember { mutableStateOf("Home") }
    NavigationBar(containerColor = Color.White) {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home", tint = Color.Black) },
            label = { Text("Home", color = Color.Black) },
            selected = selectedItem == "Home",
            onClick = {
                if (selectedItem != "Home") {
                    selectedItem = "Home"
                    onHomeNav(loggedUser)
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Person, contentDescription = "Workouts", tint = Color.Black) },
            label = { Text("Workouts", color = Color.Black) },
            selected = selectedItem == "Workouts",
            onClick = {
                if (selectedItem != "Workouts") {
                    selectedItem = "Workouts"
                    onWorkoutNav(loggedUser)
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Person, contentDescription = "Profile", tint = Color.Black) },
            selected = selectedItem == "Profile",
            label = { Text("Profile", color = Color.Black) },
            onClick = {
                if (selectedItem != "Profile") {
                    selectedItem = "Profile"
                }
            }
        )
    }
}