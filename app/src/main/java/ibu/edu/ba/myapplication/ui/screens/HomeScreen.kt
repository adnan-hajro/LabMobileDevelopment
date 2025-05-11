package ibu.edu.ba.myapplication.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ibu.edu.ba.myapplication.R
import ibu.edu.ba.myapplication.model.User
import ibu.edu.ba.myapplication.ui.screens.cards.WorkoutCard
import ibu.edu.ba.myapplication.ui.screens.navigations.BottomNavigationBar
import ibu.edu.ba.myapplication.ui.viewmodel.WorkoutViewModel
import kotlin.math.log

@Composable
fun HomeScreen(loggedUser: User, workoutViewModel: WorkoutViewModel) {
    //val workouts by workoutViewModel.workouts.collectAsState()

    LaunchedEffect(Unit) {
        //this can be added to show real workouts if exist, but for now I decided to put dummy data
        //workoutViewModel.loadWorkouts(1)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            item {
                Column(modifier = Modifier.fillMaxWidth().padding(20.dp)) {
                    Text(
                        "Welcome, ${loggedUser.fullName}!",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 20.dp)
                    )
                    Text(
                        "Push yourself to new limits today!",
                        fontSize = 16.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        "Today's Workout Suggestions",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.DarkGray,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                }
            }
            //dummy data provided because we don't have AI functionality for workout suggestion
            //on workout screen it would be real data from workouts table
            items(5) { index ->
                WorkoutCard(
                    title = "Workout ${index + 1}",
                    duration = (20..60).random(),
                    description = "Simple fast workout",
                    caloriesBurned = (200..500).random()
                )
            }
        }
    }
}