package ibu.edu.ba.myapplication.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import ibu.edu.ba.myapplication.model.pojo.WorkoutWithCategory
import ibu.edu.ba.myapplication.model.Workout

@Dao
interface WorkoutDao: BaseDao<Workout> {
    @Query("SELECT * FROM workouts WHERE userId = :userId")
    suspend fun getWorkoutsByUserId(userId: Int): List<Workout>

    @Query("SELECT * FROM workouts WHERE categoryId = :categoryId")
    suspend fun getWorkoutsByCategoryId(categoryId: Int): List<Workout>

    @Transaction
    @Query("SELECT * FROM workouts WHERE categoryId IS NOT NULL")
    suspend fun getAllWorkoutsWithCategories(): List<WorkoutWithCategory>?
}