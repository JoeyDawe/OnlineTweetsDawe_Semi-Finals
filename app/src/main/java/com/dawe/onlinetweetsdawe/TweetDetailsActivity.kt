package com.dawe.onlinetweetsdawe

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.dawe.onlinetweetsdawe.databinding.ActivityTweetDetailsBinding
import com.dawe.onlinetweetsdawe.databinding.DialogEditTweetBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Suppress("DEPRECATION")
class TweetDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTweetDetailsBinding
    private lateinit var tweet: Tweet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTweetDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tweet = intent.getParcelableExtra<Tweet>("tweet") ?: throw IllegalArgumentException("Parcelable extra 'tweet' is required")

        binding.tweetTextDetail.text = tweet.text
        binding.authorTextDetail.text = tweet.author

        binding.editTweetButton.setOnClickListener {
            showEditTweetDialog()
        }
    }

    private fun showEditTweetDialog() {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogBinding = DialogEditTweetBinding.inflate(inflater)
        val dialogView = dialogBinding.root

        dialogBinding.editTweetTextInput.setText(tweet.text)

        builder.setView(dialogView)
            .setPositiveButton("Save") { _, _ ->
                val updatedText = dialogBinding.editTweetTextInput.text.toString()
                updateTweet(updatedText)
            }
            .setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
            .create() // Use create() to create the AlertDialog

        builder.show()
    }

    private fun updateTweet(updatedText: String) {
        // Make a PUT request to your server API to update the tweet
        // Update the server endpoint and parameters accordingly
        val call = RetrofitClient.apiService.updateTweet(tweet.id, TweetRequest(updatedText))

        call.enqueue(object : Callback<Tweet> {
            override fun onResponse(call: Call<Tweet>, response: Response<Tweet>) {
                if (response.isSuccessful) {
                    tweet = response.body()!!
                    tweet.text.also { it.also { binding.tweetTextDetail.text = it } }
                } else {
                    // Handle error
                }
            }

            override fun onFailure(call: Call<Tweet>, t: Throwable) {
                // Handle failure
            }
        })
    }

}
