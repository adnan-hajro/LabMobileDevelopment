package ibu.edu.ba.myapplication.ui.screens

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import ibu.edu.ba.myapplication.StopwatchService

@Composable
fun StopwatchView() {
    val context = LocalContext.current
    val seconds = remember { mutableIntStateOf(0) }
    val isRunning = remember { mutableStateOf(false) }
    val isPaused = remember { mutableStateOf(false) }

    val ACTION_STOPWATCH_UPDATE = "STOPWATCH_UPDATE"
    val EXTRA_SECONDS = "EXTRA_SECONDS"
    val EXTRA_IS_RUNNING = "EXTRA_IS_RUNNING"
    val EXTRA_IS_PAUSED = "EXTRA_IS_PAUSED"

    DisposableEffect(Unit) {
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (intent?.action == ACTION_STOPWATCH_UPDATE) {
                    seconds.intValue = intent.getIntExtra(EXTRA_SECONDS, 0)
                    isRunning.value = intent.getBooleanExtra(EXTRA_IS_RUNNING, false)
                    isPaused.value = intent.getBooleanExtra(EXTRA_IS_PAUSED, false)
                }
            }
        }
        val filter = IntentFilter(ACTION_STOPWATCH_UPDATE)
        ContextCompat.registerReceiver(
            context,
            receiver,
            filter,
            ContextCompat.RECEIVER_NOT_EXPORTED
        )
        onDispose {
            context.unregisterReceiver(receiver)
        }
    }

    val animatedSweep = remember { Animatable(0f) }
    val currentMinuteProgress = seconds.intValue % 60

    LaunchedEffect(seconds.intValue) {
        animatedSweep.animateTo(
            targetValue = (currentMinuteProgress / 60f) * 360f,
            animationSpec = tween(durationMillis = 500, easing = LinearEasing)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(contentAlignment = Alignment.Center) {
            Canvas(modifier = Modifier.size(250.dp)) {
                // Background track
                drawArc(
                    color = Color.DarkGray,
                    startAngle = 0f,
                    sweepAngle = 360f,
                    useCenter = false,
                    style = Stroke(width = 16f, cap = StrokeCap.Round)
                )

                // Gradient progress arc
                drawArc(
                    brush = Brush.sweepGradient(
                        listOf(Color.Green, Color.Yellow, Color.Red, Color.Green)
                    ),
                    startAngle = -90f,
                    sweepAngle = animatedSweep.value,
                    useCenter = false,
                    style = Stroke(width = 16f, cap = StrokeCap.Round)
                )
            }

            Text(
                text = formatTime(seconds.intValue),
                style = MaterialTheme.typography.displayLarge,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            if (!isRunning.value) {
                Button(onClick = {
                    val intent = Intent(context, StopwatchService::class.java).apply {
                        action = "START"
                    }
                    ContextCompat.startForegroundService(context, intent)
                }) {
                    Text("Start")
                }
            } else {
                if (!isPaused.value) {
                    Button(onClick = {
                        val intent = Intent(context, StopwatchService::class.java).apply {
                            action = "PAUSE"
                        }
                        ContextCompat.startForegroundService(context, intent)
                    }) {
                        Text("Pause")
                    }
                } else {
                    Button(onClick = {
                        val intent = Intent(context, StopwatchService::class.java).apply {
                            action = "RESUME"
                        }
                        ContextCompat.startForegroundService(context, intent)
                    }) {
                        Text("Resume")
                    }
                }

                Button(onClick = {
                    val intent = Intent(context, StopwatchService::class.java).apply {
                        action = "STOP"
                    }
                    ContextCompat.startForegroundService(context, intent)
                }) {
                    Text("Stop")
                }
            }
        }
    }
}

fun formatTime(totalSeconds: Int): String {
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return "%02d:%02d".format(minutes, seconds)
}

