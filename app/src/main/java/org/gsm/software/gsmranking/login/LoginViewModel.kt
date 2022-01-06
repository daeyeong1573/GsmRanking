package org.gsm.software.gsmranking.login

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: LoginRepository) : ViewModel() {

    private val _photo_url = MutableLiveData<String>()
    val photo_url: LiveData<String> get() = _photo_url

    private val _bio = MutableLiveData<String>()
    val bio: LiveData<String> get() = _bio

    private val _nickName = MutableLiveData<String>()
    val nickName: LiveData<String> get() = _nickName

    init {
        _photo_url.value = ""
        _nickName.value = ""
        _bio.value = ""
    }


    fun getUser(githubId: String) = viewModelScope.launch {
        val response = try {
            githubId?.let { repository.getUserInfo(githubId) }
        } catch (e: Exception) {
            Log.d(TAG, "getUser: $e")
            e.printStackTrace()
            null
        }

        response.apply {
            if (this != null) {
                _nickName.value = id
                _photo_url.value = imgUrl.toString()
                Log.d(TAG, "getUser: $photo_url")
                _bio.value = bio
            } else {
                Log.d(TAG, "getUser: UserInfo is null")
            }
        }


    }
}