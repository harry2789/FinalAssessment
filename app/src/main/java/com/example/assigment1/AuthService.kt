// AuthService.kt
package com.example.assigment1

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("/sydney/auth")  // Change to your class location (footscray, sydney, or ort)
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>
}
