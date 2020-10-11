package com.pradeep.trendg.data

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.pradeep.trendg.R
import com.pradeep.trendg.constants.BASE_URL
import com.pradeep.trendg.constants.LOG_TAG
import com.pradeep.trendg.utilities.Utilities
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RepositoryRepo(private val application: Application) {

    val repositoryData = MutableLiveData<List<RepositoryModel>>()
    private val repositoryDao = RepositoryDatabase.getDatabase(application).repositoryDao()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            val data = repositoryDao.getAll()
            if (data.isEmpty()) {
                callWebService()
            } else {
                repositoryData.postValue(data)
            }
        }
    }

    @WorkerThread
    suspend fun callWebService() {
        if (Utilities().isInternetAvailable(application)) {
            Log.i(LOG_TAG, "Calling web service")
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
            val service = retrofit.create(RepositoryService::class.java)
            val call: Call<List<RepositoryModel>> = service.getRepositories()
            call.enqueue(object : Callback<List<RepositoryModel>> {
                override fun onResponse(
                    call: Call<List<RepositoryModel>>,
                    response: Response<List<RepositoryModel>>
                ) {
                    if (response.isSuccessful) {
                        val serviceData = response.body() ?: emptyList()
                        repositoryData.postValue(serviceData)
                        CoroutineScope(Dispatchers.IO).launch {
                            repositoryDao.deleteAll()
                            repositoryDao.insertRepositories(serviceData)
                        }

                    } else {
                        repositoryData.postValue(emptyList())
                        Toast.makeText(
                            application, application.getString(R.string.failure_toast_message),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<List<RepositoryModel>>, t: Throwable) {
                    Log.d(LOG_TAG, "Response = $t")
                    repositoryData.postValue(emptyList())
                    Toast.makeText(
                        application, application.getString(R.string.failure_toast_message),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        } else {
            repositoryData.postValue(emptyList())
        }
    }

    fun refreshDataFromWeb() {
        CoroutineScope(Dispatchers.IO).launch {
            callWebService()
        }
    }
}