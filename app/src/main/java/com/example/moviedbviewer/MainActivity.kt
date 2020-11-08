package com.example.moviedbviewer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviedbviewer.adapter.RecyclerViewAdapter
import com.example.moviedbviewer.models.Movie
import com.example.moviedbviewer.models.Response
import com.example.moviedbviewer.service.RetrofitInstance
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.OnItemClickedListener {
    private var page: Int = 1
    private var mode: Int = 1
    private var text: String? = null
    private val apiKey: String = "015a96e7ba5cff43e18d3770f1002595"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tResView.layoutManager = LinearLayoutManager(this)
        getMovies(page)

        searchField.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    if (query.isNotBlank()) {
                        text = query
                        page = 1
                        searchForMovies(page, query)
                    }
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        searchField.setOnCloseListener {
            page = 1
            getMovies(page)
            true
        }
    }
    val context = this
    var response: Response? = null

    private fun getMovies(page: Int) {
        val ttService = RetrofitInstance.apiService
        val call: Call<Response> = ttService.getPopularMovies(apiKey, page)
        call.enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {

                val resp: Response? = response.body()
                this@MainActivity.response = resp
                if (resp != null) {
                    val movies: List<Movie> = resp.results!!
                    val adapter = RecyclerViewAdapter(movies, context, context)
                    tResView.adapter = adapter
                }
            }
            override fun onFailure(call: Call<Response>, t: Throwable) {
                finish()
            }
        })
        mode = 1
    }

    private fun searchForMovies(page: Int, text: String) {
        val ttService = RetrofitInstance.apiService
        val call: Call<Response> = ttService.searchMovies(apiKey, page, text)
        call.enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {

                val resp: Response? = response.body()
                this@MainActivity.response = resp
                if (resp != null) {
                    val movies: List<Movie> = resp.results!!
                    val adapter = RecyclerViewAdapter(movies, context, context)
                    tResView.adapter = adapter
                }
            }
            override fun onFailure(call: Call<Response>, t: Throwable) {
                finish()
            }
        })
        mode = 2
    }

    override fun onItemClicked(item: Movie, position: Int) {
        val intent: Intent = Intent(context, MovieActivity::class.java)
        startActivity(intent)
    }

    fun nextPage(view: View) {
        if (response != null) {
            val pagesAmount = response!!.total_pages
            if (page < pagesAmount!!) {
                page++
            }
        }
        if (mode == 1)
            getMovies(page)
        else {
            text?.let { searchForMovies(page, it) }
        }
    }

    fun prevPage(view: View) {
        if (page > 1) {
            page--
        }
        if (mode == 1)
            getMovies(page)
        else {
            text?.let { searchForMovies(page, it) }
        }
    }
}
