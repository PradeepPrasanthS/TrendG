package com.pradeep.trendg.view

import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pradeep.trendg.R
import com.pradeep.trendg.adapter.TrendingGitListAdapter
import com.pradeep.trendg.viewmodel.HomeViewModel
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

        init()

        tryAgain.setOnClickListener {
            init()
        }

        swipeToRefresh.setOnRefreshListener {
            viewModel.refreshData()
            swipeToRefresh.isRefreshing = false
        }
    }

    private fun init() {
        val layoutManager = LinearLayoutManager(this)
        trendingGitList.layoutManager = layoutManager
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        viewModel.repositoryData.observe(this, { trendingGit ->
            adapter = TrendingGitListAdapter(trendingGit, application)
            trendingGitList.adapter = adapter
        })
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