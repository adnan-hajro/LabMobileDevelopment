package ibu.edu.ba.myapplication.di

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ibu.edu.ba.myapplication.dao.CategoryDao
import ibu.edu.ba.myapplication.dao.UserDao
import ibu.edu.ba.myapplication.dao.WorkoutDao
import ibu.edu.ba.myapplication.database.AppDatabase
import ibu.edu.ba.myapplication.repository.CategoryRepository
import ibu.edu.ba.myapplication.repository.CategoryRepositoryImpl
import ibu.edu.ba.myapplication.repository.UserRepository
import ibu.edu.ba.myapplication.repository.UserRepositoryImpl
import ibu.edu.ba.myapplication.repository.WorkoutRepository
import ibu.edu.ba.myapplication.repository.WorkoutRepositoryImpl
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "smart_workout_planner_v2.db",
            ).fallbackToDestructiveMigration(true).build()
    }

    @Provides
    fun providesUserDao(appDatabase: AppDatabase): UserDao = appDatabase.userDao()

    @Provides
    fun provideCategoryDao(appDatabase: AppDatabase): CategoryDao = appDatabase.categoryDao()

    @Provides
    fun provideWorkoutDao(appDatabase: AppDatabase): WorkoutDao = appDatabase.workoutDao()

    @Provides
    @Singleton
    fun provideUserRepository(
        userDao: UserDao
    ): UserRepository = UserRepositoryImpl(userDao)

    @Provides
    @Singleton
    fun provideCategoryRepository(
        categoryDao: CategoryDao
    ): CategoryRepository = CategoryRepositoryImpl(categoryDao)

    @Provides
    @Singleton
    fun provideWorkoutRepository(
        workoutDao: WorkoutDao
    ): WorkoutRepository = WorkoutRepositoryImpl(workoutDao)
}