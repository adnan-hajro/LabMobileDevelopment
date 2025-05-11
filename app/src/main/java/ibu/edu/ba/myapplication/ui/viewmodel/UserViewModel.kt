package ibu.edu.ba.myapplication.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ibu.edu.ba.myapplication.dao.CategoryDao
import ibu.edu.ba.myapplication.model.User
import ibu.edu.ba.myapplication.repository.CategoryRepository
import ibu.edu.ba.myapplication.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepo: UserRepository,
) : ViewModel() {
    private val _registrationSuccess = MutableStateFlow(false)
    val registrationSuccess: StateFlow<Boolean> = _registrationSuccess

    private val _loginStatus = MutableStateFlow<Boolean?>(null)
    val loginStatus: StateFlow<Boolean?> = _loginStatus

    private val _loggedUser = MutableStateFlow<User?>(null)
    val loggedUser: StateFlow<User?> = _loggedUser

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun register(email: String, password: String) {
        viewModelScope.launch {
            try {
                userRepo.insert(User(email=email, password=password, fullName=""))
                _registrationSuccess.value = true
            } catch (e: Exception) {
                _error.value = "Registration failed: ${e.message}"
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val user = userRepo.
            getUserByEmailAndPassword(email = email, password = password);
            _loginStatus.value = user != null;
            if (_loginStatus.value == true) {
                _loggedUser.value = user
                Log.d("logged", "_loggedUser ${loggedUser.value}")
            }
        }
    }

    // fun ...(...) { … similar pattern … }
}
