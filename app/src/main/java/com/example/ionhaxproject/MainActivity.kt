package com.example.ionhaxproject

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        val backgroundImg: ImageView = findViewById(R.id.logo)
        val sideAnimation = AnimationUtils.loadAnimation(this, R.anim.slide)
//        backgroundImg.startAnimation(sideAnimation)

        Handler().postDelayed({
            val intent = Intent(this, SideTabs::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.layout_transition_enter, R.anim.layout_transition_exit)
            finish()
        }, 1500)

    }
}