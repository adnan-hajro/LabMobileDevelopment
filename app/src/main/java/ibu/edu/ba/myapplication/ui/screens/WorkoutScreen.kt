package ibu.edu.ba.myapplication.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ibu.edu.ba.myapplication.model.User
import ibu.edu.ba.myapplication.ui.screens.cards.MyWorkoutCard
import ibu.edu.ba.myapplication.ui.viewmodel.WorkoutViewModel

@Composable
fun WorkoutScreen(loggedUser: User, workoutViewModel: WorkoutViewModel) {
//    val workouts by viewModel.workouts.collectAsState()
    val workouts by workoutViewModel.workouts.collectAsState()
//    val workoutSaved by viewModel.workoutSaved.collectAsState()
      var showAddWorkout by remember { mutableStateOf(false) }
//    val user by userViewModel.loggedUser.collectAsState()

    var isSingleColumn by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        Log.d("called", "$loggedUser")
        workoutViewModel.loadWorkouts(loggedUser.id)
    }

//    LaunchedEffect(workoutSaved) {
//        if (workoutSaved == true) {
//            viewModel.loadWorkouts(user!!.id)
//            viewModel.resetWorkoutSaved()
//            viewModel.resetWorkoutForm()
//            showAddWorkout = false
//        }
//    }

    Scaffold(
        modifier = Modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(Color(0xFF4CAF50), Color(0xFF81C786)) // Green gradient
            ),
        ),
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddWorkout = true },
                containerColor = Color(0xFF34C759), // Fresh green
                contentColor = Color.White
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add Workout")
            }
        },
        containerColor = Color(0xFFb0e3d7) // Soft off-white
    ) { padding ->
        if (showAddWorkout) {
            //AddWorkoutScreen(onDismiss = { showAddWorkout = false }, userId = user!!.id, viewModel = viewModel)
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            // Title & Grid Switch Button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Your Workouts",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Icon(
                    Icons.Filled.Refresh,
                    tint = Color.DarkGray,
                    modifier = Modifier
                        .size(28.dp)
                        .clickable { isSingleColumn = !isSingleColumn },
                    contentDescription = "Toggle Grid"
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Workout Grid
            LazyVerticalGrid(
                columns = if (isSingleColumn) GridCells.Fixed(1) else GridCells.Adaptive(minSize = 180.dp),
                contentPadding = PaddingValues(4.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(workouts) { workout ->
                    MyWorkoutCard(
                        title = "My workout",
                        duration = workout.duration,
                        caloriesBurned = workout.caloriesBurned ?: 0,
                        status = workout.status
                    )
                }
            }
        }
    }
}