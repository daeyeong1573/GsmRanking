package org.gsm.software.gsmranking

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import org.gsm.software.gsmranking.preference.DataStoreModule

@HiltAndroidApp
class MyApplication : Application() {
    private lateinit var dataStore : DataStoreModule

    companion object {
        private lateinit var myApplication: MyApplication
        fun getInstance() : MyApplication = myApplication
    }

    override fun onCreate() {
        super.onCreate()
        myApplication = this
        dataStore = DataStoreModule(this)
    }

    fun getDataStore() : DataStoreModule = dataStore

}