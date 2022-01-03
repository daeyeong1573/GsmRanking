package org.gsm.software.gsmranking.preference

import android.content.Context
import android.content.SharedPreferences
import org.gsm.software.gsmranking.model.data.User
import org.gsm.software.gsmranking.preference.PreferenceHelper.set
import org.gsm.software.gsmranking.preference.PreferenceHelper.get

class SharedManager(context: Context) {
    private val prefs: SharedPreferences = PreferenceHelper.defaultPrefs(context)

    fun saveUser(user: User) {
        prefs["id"] = user.id
    }

    fun getUser(): User {
        return User().apply {
            id = prefs["id", ""]
        }
    }

}
