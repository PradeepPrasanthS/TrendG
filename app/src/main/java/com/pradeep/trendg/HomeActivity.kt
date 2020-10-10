package com.pradeep.trendg

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pradeep.trendg.utilities.Utilities
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

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

        init()

        tryAgain.setOnClickListener {
            init()
        }

        swipeToRefresh.setOnRefreshListener {
            init()
            swipeToRefresh.isRefreshing = false
        }
    }

    private fun init() {
        if (Utilities().isInternetAvailable(this)) {
            progressLoader.visibility = View.VISIBLE
            trendingGitList.visibility = View.VISIBLE
            noInternetLayout.visibility = View.GONE
            viewModel.getTrendingGit(this).observe(this, { trendingGit ->
                adapter = TrendingGitListAdapter(trendingGit, application)
                trendingGitList.adapter = adapter
            })
        } else {
            trendingGitList.visibility = View.GONE
            noInternetLayout.visibility = View.VISIBLE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val item = menu.findItem(R.id.action_search)
        val searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(this)

        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        adapter.filter.filter(query)
        return false
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }
}