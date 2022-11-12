package com.parita.mcarousel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MainCarouselAdapter(private val list:ArrayList<CarouselData>): RecyclerView.Adapter<MainCarouselAdapter.ViewHolder>() {
    class ViewHolder(v: View): RecyclerView.ViewHolder(v) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_main_layout,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return list.size
    }
}