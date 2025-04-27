package ibu.edu.ba.myapplication.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class TaskViewModel : ViewModel() {
    private val _tasks = MutableStateFlow<List<String>>(emptyList())

    val tasks: StateFlow<List<String>> = _tasks

    fun addTask(task: String) {
        _tasks.update { currentList -> currentList + task}
    }

    fun removeTask(task: String) {
        _tasks.update {currentList -> currentList.filterNot { it == task }} //or .filter { it != task }
    }

    fun clearTasks() {
        _tasks.value = emptyList()
    }
}