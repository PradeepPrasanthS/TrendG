package com.pradeep.trendg

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: TrendingGitListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportActionBar?.let {
            it.title = getString(R.string.title_trending_repositories)
            it.elevation = 0f
        }

        val layoutManager = LinearLayoutManager(this)
        trendingGitList.layoutManager = layoutManager

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        viewModel.getTrendingGit().observe(this, { trendingGit ->
            trendingGitList.adapter = TrendingGitListAdapter(trendingGit, application)
        })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        return true
    }

    companion object {
        const val TAG = "HomeActivity"
    }
}