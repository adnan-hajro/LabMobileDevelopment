package ibu.edu.ba.myapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import ibu.edu.ba.myapplication.dao.CategoryDao
import ibu.edu.ba.myapplication.dao.UserDao
import ibu.edu.ba.myapplication.dao.WorkoutDao
import ibu.edu.ba.myapplication.model.Category
import ibu.edu.ba.myapplication.model.User
import ibu.edu.ba.myapplication.model.Workout

@Database(
    entities = [User::class, Category::class, Workout::class],
    version = 3,
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun categoryDao(): CategoryDao
    abstract fun workoutDao(): WorkoutDao
}