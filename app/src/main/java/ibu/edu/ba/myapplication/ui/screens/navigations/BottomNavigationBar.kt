package ibu.edu.ba.myapplication.ui.screens.navigations

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import ibu.edu.ba.myapplication.model.User
import ibu.edu.ba.myapplication.ui.viewmodel.UserViewModel
import kotlin.math.log

@Composable
fun BottomNavigationBar(onWorkoutNav: () -> Unit, onHomeNav: () -> Unit, onProfileNav: () -> Unit) {
    var selectedItem by remember { mutableStateOf("Home") }
    Log.d("bottom", "bottom")
    //val loggedUser by userViewModel.loggedUser.collectAsState()
    NavigationBar(containerColor = Color.White) {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home", tint = Color.Black) },
            label = { Text("Home", color = Color.Black) },
            selected = selectedItem == "Home",
            onClick = {
                if (selectedItem != "Home") {
                    selectedItem = "Home"
                    onHomeNav()
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
                    onWorkoutNav()
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
                    onProfileNav()
                }
            }
        )
    }
}