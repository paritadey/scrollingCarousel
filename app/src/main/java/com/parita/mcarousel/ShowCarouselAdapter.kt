package com.parita.mcarousel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ShowCarouselAdapter(private val list:ArrayList<String>,private val onPageChanged: (Int)->Unit): RecyclerView.Adapter<ShowCarouselAdapter.ViewHolder>() {
    inner class ViewHolder(val v: View): RecyclerView.ViewHolder(v) {
        fun bind(data: String){
            v.findViewById<TextView>(R.id.tv_text).text = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.show_item_layout,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        onPageChanged(holder.adapterPosition)
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

}