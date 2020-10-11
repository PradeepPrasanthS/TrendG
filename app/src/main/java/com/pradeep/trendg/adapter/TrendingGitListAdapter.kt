package com.pradeep.trendg.adapter

import android.app.Application
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pradeep.trendg.R
import com.pradeep.trendg.data.RepositoryModel
import java.util.*
import kotlin.collections.ArrayList

class TrendingGitListAdapter(
    private var repository: List<RepositoryModel>,
    private val application: Application
) : RecyclerView.Adapter<TrendingGitListAdapter.TrendingGitListHolder>(), Filterable {

    val allRepository = repository

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
        holder.bind(repository[position], application)
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
        private var mAvatar: ImageView
        private var mLanguageColor: ImageView

        init {
            mRepoOwnerName = itemView.findViewById(R.id.repo_owner_name)
            mRepoName = itemView.findViewById(R.id.repo_name)
            mRepoDesc = itemView.findViewById(R.id.repo_description)
            mRepoLanguage = itemView.findViewById(R.id.repo_language)
            mLanguageColor = itemView.findViewById(R.id.language_color)
            mRepoStars = itemView.findViewById(R.id.repo_stars)
            mAvatar = itemView.findViewById(R.id.avatar)
        }

        fun bind(repository: RepositoryModel, application: Application) {
            Glide
                .with(application)
                .load(repository.avatar)
                .centerCrop()
                .placeholder(R.drawable.ic_placeholder_image)
                .into(mAvatar)
            val languageColor = Color.parseColor(repository.languageColor)
            mLanguageColor.setColorFilter(languageColor, android.graphics.PorterDuff.Mode.MULTIPLY)
            mRepoOwnerName?.text = repository.author
            mRepoName?.text = repository.name
            mRepoDesc?.text = repository.description
            mRepoLanguage?.text = repository.language
            mRepoStars?.text = repository.stars
        }


    }

    override fun getFilter(): Filter {
        return repositoryFilter
    }

    private val repositoryFilter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filteredList: MutableList<RepositoryModel> = ArrayList()
            if (constraint.isEmpty()) {
                filteredList.addAll(allRepository)
            } else {
                val filterPattern =
                    constraint.toString().toLowerCase(Locale.ROOT).trim { it <= ' ' }
                for (item in repository) {
                    if (item.name.toLowerCase(Locale.ROOT).contains(filterPattern)) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        @Suppress("UNCHECKED_CAST")
        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            repository = emptyList()
            repository = results.values as List<RepositoryModel>
            notifyDataSetChanged()
        }
    }
}