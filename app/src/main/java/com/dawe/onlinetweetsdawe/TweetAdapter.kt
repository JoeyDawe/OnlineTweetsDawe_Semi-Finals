package com.dawe.onlinetweetsdawe

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dawe.onlinetweetsdawe.databinding.ItemTweetBinding


class TweetAdapter(private val tweets: List<Tweet>) :
    RecyclerView.Adapter<TweetAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemTweetBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTweetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tweet = tweets[position]
        holder.binding.apply {
            tweetText.text = tweet.text
            authorText.text = tweet.author

            root.setOnClickListener {
                val intent = Intent(root.context, TweetDetailsActivity::class.java).apply {
                    putExtra("tweetText", tweet.text)
                    putExtra("author", tweet.author)
                }
                root.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int = tweets.size
}