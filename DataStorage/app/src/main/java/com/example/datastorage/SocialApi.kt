package com.example.datastorage

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SocialApi {

    @POST("login")
    fun login(@Body login: LoginRequest):Call<User>
}