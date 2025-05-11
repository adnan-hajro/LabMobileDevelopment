package ibu.edu.ba.myapplication

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp
import ibu.edu.ba.myapplication.database.AppDatabase
import ibu.edu.ba.myapplication.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class WorkoutPlanner: Application() {
    @Inject
    lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()

        CoroutineScope(Dispatchers.IO).launch {
            //Test you can run here
            //dummy query on database just to open the connection
            database.userDao().getUserById(1)
            Log.d("DatabaseTest", "Database initialized")
        }
    }
}