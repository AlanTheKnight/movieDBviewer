package com.example.moviedbviewer.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Response {
    @SerializedName("page")
    @Expose
    val page : Int? = null

    @SerializedName("total_results")
    @Expose
    val total_results : Int? = null

    @SerializedName("total_pages")
    @Expose
    val total_pages : Int? = null

    @SerializedName("results")
    @Expose
    val results : List<Movie>? = null
}

