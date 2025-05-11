package ibu.edu.ba.myapplication.ui.navigation

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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
import ibu.edu.ba.myapplication.ui.screens.RegistrationScreen
import ibu.edu.ba.myapplication.ui.screens.WorkoutScreen
import ibu.edu.ba.myapplication.ui.screens.navigations.BottomNavigationBar
import ibu.edu.ba.myapplication.ui.viewmodel.UserViewModel
import ibu.edu.ba.myapplication.ui.viewmodel.WorkoutViewModel
import kotlinx.serialization.Serializable
import kotlin.math.log
import kotlin.reflect.typeOf

//Nested graph serializable
@Serializable object Auth

//Routes for nested Auth graph
@Serializable object Login
@Serializable object Register
@Serializable object ForgotPassword

//Nested graph Main
@Serializable object Main

@Serializable data class Home(val user: User)
@Serializable data class Workout(val user: User)
@Serializable data class Profile(val user: User)

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val showBottomBar = (currentRoute?.split('/')?.get(0)) in listOf(
        Home::class.qualifiedName,     // or whatever string your Home route serializes to
        Workout::class.qualifiedName   // same for Workout
        //same for profile
    )

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                val user = remember { navBackStackEntry!!.toRoute<Home>().user }
                BottomNavigationBar(
                    user,
                    onWorkoutNav = { navController.navigate(Workout(user)) },
                    onHomeNav = { navController.navigate(Home(user)) {
                        popUpTo(Main) { saveState = true }
                        launchSingleTop = true
                    } }
                )
            }
        }
    ) { padding ->
        NavHost(
        navController = navController,
        startDestination = Auth, modifier = Modifier.padding(padding)
    ) {
        navigation<Auth>(startDestination = Login) {
            composable<Login> { backStackEntry ->
                val authViewModel: UserViewModel = hiltViewModel(backStackEntry)
                LoginScreen(authViewModel, onRegisterNav = {
                    navController.navigate(route = Register)
                }, onSuccessLogin = {user: User ->
                    navController.navigate(route = Home(user = user)) {
                        popUpTo(route = Auth) { inclusive = true }
                    }
                })
            }
            composable<Register> { backStackEntry ->
                val authViewModel: UserViewModel = hiltViewModel(backStackEntry)
                RegistrationScreen(authViewModel, onLoginNav = {
                    navController.navigate(route = Login)
                })
            }
        }

        navigation<Main>(startDestination = Home(user = User(1, "", "", ""))) {
            composable<Home>(
                typeMap = mapOf(
                    typeOf<User>() to CustomNavType.UserType
                )
            ) { backStackEntry ->
                val workoutViewModel: WorkoutViewModel = hiltViewModel(backStackEntry)
                val loggedUser = backStackEntry.toRoute<Home>().user
                HomeScreen(loggedUser = loggedUser, workoutViewModel)
            }

            composable<Workout>(
                typeMap = mapOf(
                    typeOf<User>() to CustomNavType.UserType
                )
            ) { backStackEntry ->
                val workoutViewModel: WorkoutViewModel = hiltViewModel(backStackEntry)
                val loggedUser = backStackEntry.toRoute<Workout>().user
                WorkoutScreen(loggedUser, workoutViewModel)
            }
        }
    }
    }
}