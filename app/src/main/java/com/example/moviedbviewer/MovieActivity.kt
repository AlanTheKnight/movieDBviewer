package com.example.moviedbviewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_movie.*

class MovieActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {
    // FE:A7:E9:B0:4A:4B:D3:F0:A2:92:D6:68:AD:04:44:AE:01:99:93:B2
    val VIDEO_ID = "dQw4w9WgXcQ"
    val key: String = "AIzaSyAOSH1Mp6YLxi31sT2jvfNmX5f3cW2mnYQ"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        playerYT.initialize(key, this)
    }

    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?,
        p1: YouTubePlayer?,
        p2: Boolean
    ) {
        p1?.loadVideo(VIDEO_ID)
    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {
        Toast.makeText(this, p1.toString(), Toast.LENGTH_SHORT).show()
    }
}