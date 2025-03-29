package ibu.edu.ba.myapplication

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import ibu.edu.ba.myapplication.ui.screens.StopwatchView
import ibu.edu.ba.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                requestPermissions(
                    arrayOf(
                        android.Manifest.permission.POST_NOTIFICATIONS,
                        android.Manifest.permission.FOREGROUND_SERVICE,
                        android.Manifest.permission.FOREGROUND_SERVICE_SPECIAL_USE
                    ), 100
                )
                StopwatchView()
            }
        }
    }
}

