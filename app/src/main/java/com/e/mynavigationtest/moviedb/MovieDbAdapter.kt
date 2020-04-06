package com.e.mymovieskotlin.moviedb

import android.graphics.Color
import android.net.sip.SipSession
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.e.mymovieskotlin.domain.Movie
import com.e.mymovieskotlin.network.NetworkMovie
import com.e.mynavigationtest.R

class MovieDbAdapter(val listener: ItemClickListener): RecyclerView.Adapter<MovieDbAdapter.ViewHolder>(){


    var data = listOf<Movie>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieDbAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.movie_db_row, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MovieDbAdapter.ViewHolder, position: Int) {
        val item = data[position]
        holder.name.text = item.title

        Glide.with(holder.image.context)
            .load(item.posterBasePath + item.posterPath)
            .into(holder.image)

        holder.parent.setOnClickListener(View.OnClickListener {
            item.isSelected = ! item.isSelected
            listener.onClicked(item)
        })

        if(item.isSelected){
            holder.parent.setBackgroundColor(Color.YELLOW)
        } else {
            holder.parent.setBackgroundColor(Color.TRANSPARENT)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.findViewById(R.id.movie_db_row_name)
        val image: ImageView = itemView.findViewById(R.id.movie_db_row_image)
        val parent: View = itemView.findViewById(R.id.movie_db_row_parent)
    }

    interface ItemClickListener{
        fun onClicked(movie: Movie)
    }

}