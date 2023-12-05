package com.dawe.onlinetweetsdawe

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dawe.onlinetweetsdawe.databinding.ActivityMainBinding
import com.dawe.onlinetweetsdawe.databinding.DialogNewTweetBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var tweetAdapter: TweetAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tweetAdapter = TweetAdapter(emptyList())
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = tweetAdapter
        }

        binding.newTweetButton.setOnClickListener {
            showNewTweetDialog()
        }

        fetchData()
    }

    private fun showNewTweetDialog() {
        val dialogBinding = DialogNewTweetBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(this)
        builder.setView(dialogBinding.root)
            .setPositiveButton("Post") { _, _ ->
                val tweetText = dialogBinding.tweetTextInput.text.toString()
                postNewTweet(tweetText)
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.cancel()
            }
            .show()
    }

    private fun postNewTweet(tweetText: String) {
        // Make a POST request to your server API to post a new tweet
        // Update the server endpoint and parameters accordingly
        val tweetRequest = TweetRequest(tweetText)
        val call = RetrofitClient.apiService.postNewTweet(tweetRequest)

        call.enqueue(object : Callback<Tweet> {
            override fun onResponse(call: Call<Tweet>, response: Response<Tweet>) {
                if (response.isSuccessful) {
                    fetchData() // Refresh the tweet list after posting a new tweet
                } else {
                    // Handle error
                }
            }

            override fun onFailure(call: Call<Tweet>, t: Throwable) {
                // Handle failure
            }
        })
    }


    private fun fetchData() {
        // Fetch tweets as before
    }
}
