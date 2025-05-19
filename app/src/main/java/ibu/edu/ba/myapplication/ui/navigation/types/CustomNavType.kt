package ibu.edu.ba.myapplication.ui.navigation.types

import android.net.Uri
import androidx.navigation.NavType
import androidx.savedstate.SavedState
import ibu.edu.ba.myapplication.model.Category
import ibu.edu.ba.myapplication.model.User
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object CustomNavType {
    val UserType = object : NavType<User>(
        isNullableAllowed = false
    ) {
        override fun put(bundle: SavedState, key: String, value: User) {
            bundle.putString(key, Json.encodeToString(value))
        }

        override fun get(bundle: SavedState, key: String): User? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): User {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: User): String {
            return Uri.encode(Json.encodeToString(value))
        }
    }
    val CategoryType = object : NavType<Category>(
        isNullableAllowed = false
    ) {
        override fun put(bundle: SavedState, key: String, value: Category) {
            TODO("Not yet implemented")
        }

        override fun get(bundle: SavedState, key: String): Category? {
            TODO("Not yet implemented")
        }

        override fun parseValue(value: String): Category {
            TODO("Not yet implemented")
        }

        override fun serializeAsValue(value: Category): String {
            return Uri.encode(Json.encodeToString(value))
        }

    }
}