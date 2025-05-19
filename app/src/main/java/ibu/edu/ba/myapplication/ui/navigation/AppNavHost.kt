package ibu.edu.ba.myapplication.ui.navigation

import android.util.Log
import android.window.SplashScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import ibu.edu.ba.myapplication.model.User
import ibu.edu.ba.myapplication.ui.navigation.types.CustomNavType
import ibu.edu.ba.myapplication.ui.screens.HomeScreen
import ibu.edu.ba.myapplication.ui.screens.LoginScreen
import ibu.edu.ba.myapplication.ui.screens.ProfileScreen
import ibu.edu.ba.myapplication.ui.screens.RegistrationScreen
import ibu.edu.ba.myapplication.ui.screens.WorkoutScreen
import ibu.edu.ba.myapplication.ui.screens.navigations.BottomNavigationBar
import ibu.edu.ba.myapplication.ui.viewmodel.UserViewModel
import ibu.edu.ba.myapplication.ui.viewmodel.WorkoutViewModel
import kotlinx.serialization.Serializable
import kotlin.math.log
import kotlin.reflect.KClass
import kotlin.reflect.typeOf

//Nested graph serializable
@Serializable object Auth

//Routes for nested Auth graph
@Serializable object Login
@Serializable object Register
@Serializable object ForgotPassword

//Nested graph Main
@Serializable object Main

//Home route can be done differently without passing whole object, but only passing the userId
@Serializable object Home
@Serializable object Workout
@Serializable object Profile

/**
 * @author Adnan Hajro
 */

@Composable
fun AppNavHost(userViewModel: UserViewModel = hiltViewModel<UserViewModel>()) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val isLoading by userViewModel.isLoading.collectAsState()
    val loggedUser by userViewModel.loggedUser.collectAsState()
    val startDestination: Any = Auth

    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }

    LaunchedEffect(loggedUser) {
        if (loggedUser == null) {
            navController.navigate(Login) {
                popUpTo(Main) { inclusive = true }
                launchSingleTop = true
            }
        } else {
            userViewModel.setLoadingFalse()
            navController.navigate(Home) {
                popUpTo(Auth) { inclusive = true }
                launchSingleTop = true
            }
        }
    }

    val showBottomBar = (currentRoute?.split('/')?.get(0)) in listOf(
        Home::class.qualifiedName,     // or whatever string your Home route serializes to
        Workout::class.qualifiedName,   // same for Workout
        Profile::class.qualifiedName
    )

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomNavigationBar(
                    onWorkoutNav = { navController.navigate(Workout) },
                    onHomeNav = { navController.navigate(Home) {
                        popUpTo(Main)
                        launchSingleTop = true
                    } },
                    onProfileNav = {
                        navController.navigate(Profile)
                    }
                )
            }
        }
    ) { padding ->
        NavHost(
        navController = navController,
        startDestination = startDestination, modifier = Modifier.padding(padding)
    ) {
        navigation<Auth>(startDestination = Login) {
            composable<Login> { backStackEntry ->
                if (loggedUser == null) {
                    LoginScreen(userViewModel, onRegisterNav = {
                        navController.navigate(route = Register)
                    }, onSuccessLogin = {
                        navController.navigate(route = Home) {
                            popUpTo(route = Auth) { inclusive = true }
                        }
                    })
                } else {
                    //this can be extracted as some helper UI component
                    Box(
                        Modifier
                            .fillMaxSize()
                            .background(Color.White),
                        contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
            }

            composable<Register> { backStackEntry ->
                RegistrationScreen(userViewModel, onLoginNav = {
                    navController.navigate(route = Login)
                })
            }
        }

        navigation<Main>(startDestination = Home) {
            composable<Home>(
                typeMap = mapOf(
                    typeOf<User>() to CustomNavType.UserType
                )
            ) { backStackEntry ->
                val workoutViewModel: WorkoutViewModel = hiltViewModel(backStackEntry)
                HomeScreen(workoutViewModel, userViewModel)
            }

            composable<Workout>(
                typeMap = mapOf(
                    typeOf<User>() to CustomNavType.UserType
                )
            ) { backStackEntry ->
                val workoutViewModel: WorkoutViewModel = hiltViewModel(backStackEntry)
                WorkoutScreen(workoutViewModel, userViewModel, loggedUser)
            }

            composable<Profile> { backStackEntry ->
                //profileViewModel needs to be added
                loggedUser?.let {
                    ProfileScreen(
                        userViewModel, onLogoutSuccess = {},
                        loggedUser = it
                    )
                }
            }
        }
    }
    }
}