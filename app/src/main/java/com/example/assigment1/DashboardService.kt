// DashboardService.kt
package com.example.assigment1

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DashboardService {

    @GET("/dashboard/{keypass}")
    fun getDashboardData(@Path("keypass") keypass: String): Call<DashboardResponse>
}
