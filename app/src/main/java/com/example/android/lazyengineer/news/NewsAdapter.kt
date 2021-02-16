package com.example.android.lazyengineer.news

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.lazyengineer.R
import com.example.android.lazyengineer.databinding.RecyclerviewNewBinding
import kotlinx.android.synthetic.main.recyclerview_new.view.*

class NewsAdapter(
        private val context: Context,
        private val news: List<News>
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(
            val recyclerviewNewBinding: RecyclerviewNewBinding
    ) : RecyclerView.ViewHolder(recyclerviewNewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder =
            NewsViewHolder(
                    DataBindingUtil.inflate(
                            LayoutInflater.from(parent.context),
                            R.layout.recyclerview_new,
                            parent,
                            false
                    )
            )


    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.recyclerviewNewBinding.news = news[position]
        holder.recyclerviewNewBinding.newsSourceButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(news[position].link)
            if (intent.resolveActivity(context.packageManager) != null) {
                context.startActivity(intent)
            } else Toast.makeText(context, "browser not found", Toast.LENGTH_SHORT).show()
        }
        holder.recyclerviewNewBinding.root.setOnClickListener {
            holder.recyclerviewNewBinding.apply {
                if (it.news_description.maxLines != 50)
                    it.news_description.maxLines = 50
                else it.news_description.maxLines = 4
            }
        }
    }

    override fun getItemCount() = news.size

}