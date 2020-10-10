package com.pradeep.trendg

import android.app.Application
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pradeep.trendg.api.RepositoryModel

class TrendingGitListAdapter(
    private val repository: List<RepositoryModel>,
    private val application: Application
) :
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
            Log.i("Info", languageColor.toString())
            mLanguageColor.setColorFilter(languageColor, android.graphics.PorterDuff.Mode.MULTIPLY)
            mRepoOwnerName?.text = repository.author
            mRepoName?.text = repository.name
            mRepoDesc?.text = repository.description
            mRepoLanguage?.text = repository.language
            mRepoStars?.text = repository.stars
        }


    }
}