package com.pradeep.trendg

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

class TrendingGitListAdapter(private val repository: ArrayList<Repository>) :
    RecyclerView.Adapter<TrendingGitListAdapter.TrendingGitListHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrendingGitListHolder {
        val inflatedView = parent.inflate(R.layout.trending_repository_layout, false)
        return TrendingGitListHolder(inflatedView)
    }

    override fun onBindViewHolder(
        holder: TrendingGitListHolder,
        position: Int
    ) {
        holder.bind(repository[position])
    }

    private fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }

    override fun getItemCount(): Int {
        return repository.size
    }

    class TrendingGitListHolder(v: View) : RecyclerView.ViewHolder(v) {

        private var mRepoOwnerName: TextView? = null
        private var mRepoName: TextView? = null
        private var mRepoDesc: TextView? = null
        private var mRepoLanguage: TextView? = null
        private var mRepoStars: TextView? = null

        init {
            mRepoOwnerName = itemView.findViewById(R.id.repo_owner_name)
            mRepoName = itemView.findViewById(R.id.repo_name)
            mRepoDesc = itemView.findViewById(R.id.repo_description)
            mRepoLanguage = itemView.findViewById(R.id.repo_language)
            mRepoStars = itemView.findViewById(R.id.repo_stars)
        }

        fun bind(repository: Repository) {
            mRepoOwnerName?.text = repository.repoOwnerName
            mRepoName?.text = repository.repoName
            mRepoDesc?.text = repository.repoDesc
            mRepoLanguage?.text = repository.repoLanguage
            mRepoStars?.text = repository.repoStars
        }


    }
}