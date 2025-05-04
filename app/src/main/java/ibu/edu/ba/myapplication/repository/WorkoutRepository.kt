package ibu.edu.ba.myapplication.repository

import ibu.edu.ba.myapplication.model.Workout

interface WorkoutRepository: BaseRepository<Workout> {
    suspend fun getWorkoutsByUserId(userId: Int): List<Workout>;

    suspend fun getWorkoutsByCategoryId(categoryId: Int): List<Workout>;
}