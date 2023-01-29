package com.somename.newsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.somename.newsapp.network.NewsData

class MainAdapter(val NewsList: List<NewsData>): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    inner class MainViewHolder(private val itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindData(news: NewsData){
            val headline = itemView.findViewById<TextView>(R.id.newsHeadline)
            val image = itemView.findViewById<ImageView>(R.id.newsImage)
            val author = itemView.findViewById<TextView>(R.id.newsAuthor)
            val date = itemView.findViewById<TextView>(R.id.newsDate)
            val readButton = itemView.findViewById<Button>(R.id.btnContent)

            headline.text = news.newsPublication
            image.load(news.newsImage){
                transformations(CircleCropTransformation())
            }
            author.text = news.articleAuthor
            date.text = news.publishedAt
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.feed_view,parent,false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bindData(NewsList[position])
    }

    override fun getItemCount(): Int {
        return NewsList.size
    }
}