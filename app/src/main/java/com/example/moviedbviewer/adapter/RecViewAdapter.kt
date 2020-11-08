package com.example.moviedbviewer.adapter


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviedbviewer.R
import com.example.moviedbviewer.models.Movie


class RecyclerViewAdapter(var items: List<Movie>, var clickListener: OnItemClickedListener, val context: AppCompatActivity) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Movie, action: OnItemClickedListener) {
            val url = "https://image.tmdb.org/t/p/w500" + item.poster_path
            itemView.findViewById<TextView>(R.id.mTitle).text = item.title
            val image = itemView.findViewById<ImageView>(R.id.imageView2)
            Glide.with(context).load(url).placeholder(R.drawable.poster).into(image)

            itemView.setOnClickListener {
                action.onItemClicked(item, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.tcarditem,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], clickListener)
    }

    override fun getItemCount(): Int = items.size

    interface OnItemClickedListener {
        fun onItemClicked(item: Movie, position: Int)
    }
}