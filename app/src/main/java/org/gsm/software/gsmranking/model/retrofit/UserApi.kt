package org.gsm.software.gsmranking.model.retrofit

import org.gsm.software.gsmranking.model.data.UserInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {

    @GET("users/{userId}")
    suspend fun getUserInfo(
        @Path("userId") id : String
    ):UserInfo

}