package ibu.edu.ba.myapplication.session

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    companion object {
        private val USER_ID = intPreferencesKey("user_id")
    }

    suspend fun saveUserId(userId: Int) {
        dataStore.edit {prefs ->
            prefs[USER_ID] = userId
        }
    }

    suspend fun clearSession() {
        dataStore.edit {it.clear()}
    }

    val userIdFlow: Flow<Int?> =
        dataStore.data.map { prefs -> prefs[USER_ID] }
}