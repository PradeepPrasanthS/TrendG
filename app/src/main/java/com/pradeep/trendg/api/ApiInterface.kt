package com.pradeep.trendg.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {

    @GET("repositories")
    fun getRepositories(
        @Query("language") language: String,
        @Query("since") since: String,
        @Query("spoken_language_code") spoken_language_code: String
    ): Call<ArrayList<RepositoryModel>>

}