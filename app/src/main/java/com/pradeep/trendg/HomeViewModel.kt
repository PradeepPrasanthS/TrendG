package com.pradeep.trendg

import android.app.Application
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.pradeep.trendg.api.ApiInterface
import com.pradeep.trendg.api.RepositoryModel
import com.pradeep.trendg.api.RetrofitClientInstance
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var trendingGit: MutableLiveData<List<RepositoryModel>>

    fun getTrendingGit(activity: HomeActivity): MutableLiveData<List<RepositoryModel>> {
        if (!::trendingGit.isInitialized) {
            trendingGit = MutableLiveData()
            loadTrendingGit(activity)
        }
        return trendingGit
    }

    private fun loadTrendingGit(activity: HomeActivity) {
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
                    activity.progressLoader.visibility = View.GONE
                } else {
                    Toast.makeText(
                        activity,
                        activity.getString(R.string.failure_toast_message),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<RepositoryModel>>, t: Throwable) {
                Log.d("TAG", "Response = $t")
            }
        })
    }
}