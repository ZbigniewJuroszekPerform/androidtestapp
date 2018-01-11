package com.sample.perform.app.ui.news

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.sample.perform.app.R
import com.sample.perform.app.data.model.ArticlesItem
import javax.inject.Inject

class NewsAdapter
@Inject
constructor() : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    var items = emptyList<ArticlesItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = items[position]
        holder.textHeadline.text = item.headline
        holder.textTeaser.text = item.teaser
        holder.textPubDate.text = item.lastUpdateTime.toString()
        holder.itemView.tag = item
    }

    override fun getItemCount(): Int = items.size

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.tvHeadline)
        lateinit var textHeadline: TextView

        @BindView(R.id.tvTeaser)
        lateinit var textTeaser: TextView

        @BindView(R.id.tvPubDate)
        lateinit var textPubDate: TextView


        @BindView(R.id.llTextContainer)
        lateinit var llTextContainer: RelativeLayout
        init {
            ButterKnife.bind(this, itemView)
        }

    }
}
