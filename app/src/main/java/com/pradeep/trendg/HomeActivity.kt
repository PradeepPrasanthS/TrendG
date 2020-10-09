package com.pradeep.trendg

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity

const val TAG = "HomeActivity"

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportActionBar?.let {
            it.title = getString(R.string.title_trending_repositories)
            it.elevation = 0f
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        return true
    }
}