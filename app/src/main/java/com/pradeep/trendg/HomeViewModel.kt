package com.pradeep.trendg

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.pradeep.trendg.api.ApiInterface
import com.pradeep.trendg.api.RepositoryModel
import com.pradeep.trendg.api.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var trendingGit: MutableLiveData<List<RepositoryModel>>

    fun getTrendingGit(): MutableLiveData<List<RepositoryModel>> {
        if (!::trendingGit.isInitialized) {
            trendingGit = MutableLiveData()
            loadTrendingGit()
        }
        return trendingGit
    }

    private fun loadTrendingGit() {
        val apiService: ApiInterface =
            RetrofitClientInstance().getClient().create(ApiInterface::class.java)

        val call: Call<List<RepositoryModel>> = apiService.getRepositories("", "", "")
        call.enqueue(object : Callback<List<RepositoryModel>> {
            override fun onResponse(
                call: Call<List<RepositoryModel>>,
                response: Response<List<RepositoryModel>>
            ) {
                if (response.isSuccessful) {
                    trendingGit.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<RepositoryModel>>, t: Throwable) {
                Log.d("TAG", "Response = $t")
            }
        })
    }
}