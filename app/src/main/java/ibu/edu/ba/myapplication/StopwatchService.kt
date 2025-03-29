package ibu.edu.ba.myapplication

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class StopwatchService : Service() {
    private var seconds = 0
    private var job: Job? = null
    private var isPaused = false
    private var isRunning = true
    private var notifyAtSeconds = listOf(10, 20, 30)

    val ACTION_STOPWATCH_UPDATE = "STOPWATCH_UPDATE"
    val EXTRA_SECONDS = "EXTRA_SECONDS"
    val EXTRA_IS_RUNNING = "EXTRA_IS_RUNNING"
    val EXTRA_IS_PAUSED = "EXTRA_IS_PAUSED"


    private fun broadcastStopwatchUpdate() {
        val intent = Intent(ACTION_STOPWATCH_UPDATE).apply {
            setPackage("ibu.edu.ba.myapplication")
            putExtra(EXTRA_SECONDS, seconds)
            putExtra(EXTRA_IS_RUNNING, isRunning)
            putExtra(EXTRA_IS_PAUSED, isPaused)
        }
        sendBroadcast(intent)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotificationChannel()

        when (intent?.action) {
            "START" -> startStopwatch()
            "PAUSE" -> isPaused = true
            "RESUME" -> isPaused = false
            "STOP" -> {
                isRunning = false;
                broadcastStopwatchUpdate()
                stopSelf()
            }
        }

        return START_STICKY
    }

    private fun startStopwatch() {
        startForeground(1, buildNotification("Stopwatch started"))

        job = CoroutineScope(Dispatchers.Default).launch {
            while (true) {
                delay(1000)
                if (isPaused) {
                    broadcastStopwatchUpdate()
                    continue
                }
                seconds++

                if (seconds in notifyAtSeconds) {
                    showPopupNotification("$seconds seconds passed!")
                }

                updateNotification("Running: $seconds seconds")
                broadcastStopwatchUpdate()
            }
        }
    }

    private fun buildNotification(text: String): Notification {
        val pauseIntent = createPendingIntent("PAUSE")
        val resumeIntent = createPendingIntent("RESUME")
        val stopIntent = createPendingIntent("STOP")

        return NotificationCompat.Builder(this, "stopwatch_channel")
            .setContentTitle("Stopwatch")
            .setContentText(text)
            .setSmallIcon(android.R.drawable.ic_popup_sync)
            .setOngoing(true)
            .addAction(0, "Pause", pauseIntent)
            .addAction(0, "Resume", resumeIntent)
            .addAction(0, "Stop", stopIntent)
            .setSound(null)
            .setDefaults(0)
            .build()
    }

    private fun updateNotification(text: String) {
        val notification = buildNotification(text)
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(1, notification)
    }

    private fun showPopupNotification(text: String) {
        val notification = NotificationCompat.Builder(this, "stopwatch_channel")
            .setContentTitle("Time Alert")
            .setContentText(text)
            .setSmallIcon(android.R.drawable.ic_popup_sync)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(Random.nextInt(), notification)
    }

    private fun createPendingIntent(action: String): PendingIntent {
        val intent = Intent(this, StopwatchActionReceiver::class.java).apply {
            this.action = action
        }
        return PendingIntent.getBroadcast(this, action.hashCode(), intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "stopwatch_channel",
                "Stopwatch Channel",
                NotificationManager.IMPORTANCE_HIGH
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

    override fun onDestroy() {
        job?.cancel()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}