package ibu.edu.ba.myapplication.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ibu.edu.ba.myapplication.dao.WorkoutDao
import ibu.edu.ba.myapplication.model.Workout
import ibu.edu.ba.myapplication.repository.WorkoutRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutViewModel @Inject constructor(private val workoutRepository: WorkoutRepository): ViewModel() {
    private val _workouts = MutableStateFlow<List<Workout>>(emptyList())
    val workouts: StateFlow<List<Workout>> = _workouts

    fun loadWorkouts(userId: Int) {
        viewModelScope.launch {
            val list = workoutRepository.getWorkoutsByUserId(userId)
            _workouts.value = list
        }
    }
}