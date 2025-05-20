package ibu.edu.ba.myapplication.repository

import android.util.Log
import ibu.edu.ba.myapplication.dao.WorkoutDao
import ibu.edu.ba.myapplication.model.Workout
import ibu.edu.ba.myapplication.model.pojo.WorkoutWithCategory
import javax.inject.Inject

class WorkoutRepositoryImpl @Inject constructor(private val workoutDao: WorkoutDao): WorkoutRepository {
    override suspend fun getWorkoutsByUserId(userId: Int): List<Workout> {
        return workoutDao.getWorkoutsByUserId(userId)
    }

    override suspend fun getWorkoutsByCategoryId(categoryId: Int): List<Workout> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllWorkoutsWithCategories(): List<WorkoutWithCategory>? {
        return workoutDao.getAllWorkoutsWithCategories();
    }

    override suspend fun insert(entity: Workout) {
        TODO("Not yet implemented")
    }

    override suspend fun update(entity: Workout) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(entity: Workout) {
        TODO("Not yet implemented")
    }
}