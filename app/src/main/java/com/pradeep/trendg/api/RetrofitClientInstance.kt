package com.pradeep.trendg.api

import com.pradeep.trendg.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClientInstance {

    private lateinit var retrofit: Retrofit

    fun getClient(): Retrofit {

        val httpClient = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logging)
        }

        retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .baseUrl(BASE_URL)
            .build()

        return retrofit
    }

    companion object {
        const val BASE_URL = "https://private-b877b-githubtrendingapi.apiary-mock.com/"
    }
}