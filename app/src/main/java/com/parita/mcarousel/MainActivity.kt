package com.parita.mcarousel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val carousel = findViewById<Button>(R.id.carousel)
        val carouselNo = findViewById<EditText>(R.id.carouselNo)
        if(carouselNo.text.toString()!=null) {
            carousel.setOnClickListener {
                val send = Intent(this@MainActivity, CarouselActivity::class.java)
                val data = Bundle()
                data.putString("carousel", carouselNo.text.toString())
                startActivity(send.putExtras(data))
            }
        }
    }
}