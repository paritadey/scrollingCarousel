package com.parita.mcarousel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class CarouselAdapter(private val list: ArrayList<CarouselData>,private val context: Context) : RecyclerView.Adapter<CarouselAdapter.ViewHolder>() {

    private var colorRes: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                when (viewType) {
                    1 -> {
                        R.layout.medium_dot_layout
                    }
                    2 -> {
                        R.layout.small_dot_layout
                    }
                    3 -> {
                        R.layout.round_rect_layout
                    }
                    4 -> {
                        R.layout.no_dot
                    }
                    else -> {
                        R.layout.no_dot
                    }
                }, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(colorRes!=-1){
            holder.bind(colorRes)
        }
    }

    override fun getItemCount(): Int {
        return list.size;
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].viewType
    }

    inner class ViewHolder(private val v: View) : RecyclerView.ViewHolder(v) {
        fun bind(color: Int){
            v.findViewById<CardView>(R.id.dot).setCardBackgroundColor(context.resources.getColor(color))
        }
    }

    fun setDotColorResource(res:Int){
        colorRes = res
    }

}