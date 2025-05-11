package ibu.edu.ba.myapplication.ui.screens.cards

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ibu.edu.ba.myapplication.R

@Composable
fun MyWorkoutCard(
    title: String,
    duration: Int,
    caloriesBurned: Int?,
    status: String
) {
    val statusColor = when (status) {
        "Completed" -> Color.Green
        "Pending" -> Color.Yellow
        "Not Started" -> Color.Red
        "Stopped" -> Color.Gray
        "In Progress" -> Color.Blue
        else -> Color.White
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .height(150.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Black.copy(alpha = 0.6f))
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Workout Image
            Image(
                painter = painterResource(id = R.drawable.dumbells),
                contentDescription = "Workout Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier.padding(10.dp)
                    .align(Alignment.TopEnd)
                    .background(statusColor, shape = RoundedCornerShape(12.dp))
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Text(
                    text = status,
                    fontSize = 12.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f))
                    .padding(16.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(title, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
                Spacer(modifier = Modifier.height(4.dp))
                Text("🕒 $duration min  |  🔥 $caloriesBurned kcal", color = Color.White, fontSize = 14.sp)
            }
        }
    }
}