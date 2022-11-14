package com.parita.mcarousel

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper


class CarouselActivity : AppCompatActivity() {

    private var list = arrayListOf<String>()
    var count = 0
    lateinit var button: Button
    private var directionForward: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carousel)
        var carouselData = intent.getStringExtra("carousel")
        val range = carouselData?.toInt() ?: 1
        (0 until range).map { list.add("$it") }
        val cl = findViewById<CustomViewTest>(R.id.cl)
        cl.sentCarouselLength(list.size)
        cl.colorRes(CarouselColor(R.color.gray, R.color.white)) // non -reverse
        //cl.colorRes(CarouselColor(R.color.white, R.color.gray)) //reverse
        val showCarouselAdapter = ShowCarouselAdapter(list) { position ->
            cl.setSelectedPosition(position)
        }


        val showCarousel = findViewById<RecyclerView>(R.id.rv_show_carousel)
        showCarousel.adapter = showCarouselAdapter
        showCarouselAdapter.notifyDataSetChanged()


        val helper: SnapHelper = LinearSnapHelper()
        helper.attachToRecyclerView(showCarousel)


        button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            cl.setSelectedPosition((count) % list.size)
            setButtonText((count) % list.size)
            if (directionForward) {
                count++
                if (count == list.size) {
                    directionForward = false
                    count = list.size - 2
                }
            } else {
                count--
                if (count == -1) {
                    directionForward = true
                    count = 1
                }
            }

        }
    }

    private fun setButtonText(count: Int) {
        button.text = "I am at page : ${count + 1}"
    }
}