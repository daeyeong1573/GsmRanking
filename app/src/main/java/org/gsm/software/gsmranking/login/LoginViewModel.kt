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

    private val _loginCheck = MutableLiveData<Boolean>()
    val loginCheck: LiveData<Boolean> get() = _loginCheck

    init {
        _photo_url.value = ""
        _nickName.value = ""
        _bio.value = ""
        _loginCheck.value = true
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
                _photo_url.value = imgUrl
                _bio.value = bio
                _loginCheck.value = false
                Log.d(TAG, "getUser: $_loginCheck")
            } else {
                Log.d(TAG, "getUser: UserInfo is null")
            }
        }
    }
}