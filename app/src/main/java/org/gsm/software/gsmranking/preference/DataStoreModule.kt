package org.gsm.software.gsmranking.preference

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

@ActivityRetainedScoped
class DataStoreModule @Inject constructor(@ApplicationContext private val context: Context) {
    private val Context.dataStore by preferencesDataStore(name = "dataStore")

    private val stringKey = stringPreferencesKey("id")

    val idFlow: Flow<String> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[stringKey] ?: ""
        }

    // String값을 stringKey 키 값에 저장
    suspend fun setUser(id: String) {
        context.dataStore.edit { preferences ->
            preferences[stringKey] = id
        }
    }

}