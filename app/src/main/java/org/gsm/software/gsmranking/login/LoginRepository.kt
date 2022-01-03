package org.gsm.software.gsmranking.login

import org.gsm.software.gsmranking.model.retrofit.UserApi
import javax.inject.Inject

class LoginRepository @Inject constructor(private val userApi: UserApi) {
    suspend fun getUserInfo(userId : String) = userApi.getUserInfo(userId)
}