package com.pradeep.trendg

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

const val TAG = "HomeActivity"

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        actionBar?.title = getString(R.string.title_trending_repositories)
    }
}