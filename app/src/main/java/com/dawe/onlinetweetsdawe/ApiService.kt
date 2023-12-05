import retrofit2.Call
import retrofit2.http.*


data class Tweet(val id: String, val text: String, val author: String)

data class TweetRequest(val updatedText: String)

data class TweetResponse(val id: String, val text: String, val author: String)

@Suppress("UNUSED")
interface ApiService {
    @GET("https://eldroid.online/tweet/dawe")
    fun getAllTweets(@Query("lastname") lastName: String): Call<List<Tweet>>

    @GET("tweet/dawe/{tweet_id}")
    fun getSpecificTweet(
        @Query("lastname") lastName: String,
        @Path("tweet_id") tweetId: String
    ): Call<Tweet>

    @POST("tweet/dawe")
    fun createTweet(
        @Query("lastname") lastName: String,
        @Body tweetRequest: TweetRequest
    ): Call<TweetResponse>

    @PUT("tweet/dawe/{tweet_id}")
    fun updateTweet(
        @Query("lastname") lastName: String,
        @Path("tweet_id") tweetId: String,
        @Body tweetRequest: TweetRequest
    ): Call<TweetResponse>

    @DELETE("tweet/dawe/{tweet_id}")
    fun deleteTweet(
        @Query("lastname") lastName: String,
        @Path("tweet_id") tweetId: String
    ): Call<TweetResponse>
}
