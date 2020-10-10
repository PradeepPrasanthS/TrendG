package com.pradeep.trendg.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.pradeep.trendg.data.RepositoryRepo

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val dataRepo = RepositoryRepo(application)
    val repositoryData = dataRepo.repositoryData

    fun refreshData() {
        dataRepo.refreshDataFromWeb()
    }
}