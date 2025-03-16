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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ibu.edu.ba.myapplication.R

@Composable
fun CardView(title: String, description: String) {
    Card(
        modifier = Modifier.fillMaxWidth().
        //border(2.dp, color = Color.Black, shape = RoundedCornerShape(24.dp)).
        height(120.dp).padding(start = 15.dp, end = 15.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF3F3F8)),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row(
            //horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.dumbells),
                contentDescription = "Workout suggestion card image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(110.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
            Column(
                modifier = Modifier.weight(1f).fillMaxHeight().padding(vertical = 15.dp)
//                    .background(color = Color.Blue),
//              verticalArrangement = Arrangement.SpaceBetween
            ) {
                    Text(
                        text = "Chest-day workout",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = "Mainly focused on lower chest + triceps",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        maxLines = 2,
                    )
            }
            Box(
                modifier = Modifier.fillMaxHeight().weight(0.25f)
                    .background(Brush.verticalGradient(
                        colors = listOf(Color(0xFF4CAF50), Color(0xFF81C784)),
                    ), shape = RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    tint = Color.White,
                    contentDescription = "Favorite workout",
                    modifier = Modifier.size(25.dp).clickable {
                        println("I want to make this workout as one of my favorites.")
                    }
                )
            }
        }
    }
}


@Composable
fun ForcedCardView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ) {
        Spacer(modifier = Modifier.size(10.dp))
        CardView(title = "Chest-day workout", description = "Mainly focused on lower chest + triceps")
        Spacer(modifier = Modifier.size(10.dp))
        CardView(title = "Chest-day workout", description = "Mainly focused on lower chest + triceps")
        Spacer(modifier = Modifier.size(10.dp))
        CardView(title = "Chest-day workout", description = "Mainly focused on lower chest + triceps")
    }
}