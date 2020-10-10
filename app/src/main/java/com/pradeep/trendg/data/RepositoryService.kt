package com.pradeep.trendg.data

import retrofit2.Call
import retrofit2.http.GET

interface RepositoryService {
    @GET("repositories")
    fun getRepositories(): Call<List<RepositoryModel>>
}