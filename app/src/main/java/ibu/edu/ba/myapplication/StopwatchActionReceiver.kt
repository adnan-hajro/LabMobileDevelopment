package ibu.edu.ba.myapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat

class StopwatchActionReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val serviceIntent = Intent(context, StopwatchService::class.java)
        serviceIntent.action = intent?.action
        ContextCompat.startForegroundService(context!!, serviceIntent)
    }
}