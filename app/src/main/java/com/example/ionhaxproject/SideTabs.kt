package com.example.ionhaxproject

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SideTabs : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_side_tabs)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val mainLayout = findViewById<RelativeLayout>(R.id.main)

        val imageViews = listOf(
            R.id.categories_image,
            R.id.drivesImg,
            R.id.testImg,
            R.id.analysisImg,
            R.id.userImg,
            R.id.settingsImg,
            R.id.logoutImg
        )

        val textViewIds = listOf(
            R.id.categories_text,
            R.id.drivesText,
            R.id.testTxt,
            R.id.analysisTxt,
            R.id.userTxt,
            R.id.settingsTxt,
            R.id.logout
        )

        for (i in imageViews.indices) {
            val imageView = findViewById<ImageView>(imageViews[i])
            val textView = findViewById<TextView>(textViewIds[i])

            imageView.setOnLongClickListener {
                val textView = TextView(this@SideTabs).apply {
                    text = textView.text.toString()
                    textSize = 15f
                    setTextColor(resources.getColor(android.R.color.white))
                    setBackgroundResource(R.drawable.bg)
                    setPadding(25, 25,25, 25)
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    ).apply {
                        leftMargin = imageView.right + 80
                        topMargin = imageView.top + 20
                    }
                }
                mainLayout.addView(textView)
                textView.postDelayed({
                    mainLayout.removeView(textView)
                }, 1200)
                true
            }
        }
    }
}
