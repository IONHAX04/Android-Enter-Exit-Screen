package com.example.ionhaxproject

import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SignUpActivity : AppCompatActivity() {

    private lateinit var fullscreenButton: Button
    private lateinit var exitFullscreenButton: Button
    private lateinit var mainLayout: View
    private lateinit var countTextView: TextView
    private var pullDownCount = 0
    private var isFullScreen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        // Call requestWindowFeature before adding content
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        // Set flags before calling super.onCreate()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_sign_up)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mainLayout = findViewById(R.id.main)
        fullscreenButton = findViewById(R.id.fullscreenButton)
        exitFullscreenButton = findViewById(R.id.exitFullscreenButton)
        countTextView = findViewById(R.id.countTextView)

        fullscreenButton.setOnClickListener {
            enterFullScreen()
        }

        exitFullscreenButton.setOnClickListener {
            exitFullScreen()
        }

        // Listen for changes in system UI visibility to detect notification drawer pull-down
        mainLayout.setOnSystemUiVisibilityChangeListener { visibility ->
            if (isFullScreen && visibility and View.SYSTEM_UI_FLAG_FULLSCREEN == 0) {
                // Notification drawer is pulled down while in full-screen mode
                pullDownCount++
                updatePullDownCount()
                if (pullDownCount >= 6) { // Check if the count is 6
                    exitFullScreen() // Call exitFullScreen if count reaches 6
                }
            }
        }
    }

    override fun onBackPressed() {
        if (isFullScreen) {
            // Only increase the pull down count when in full-screen mode
            pullDownCount++
            updatePullDownCount()
            if (pullDownCount >= 6) { // Check if the count is 6
                exitFullScreen() // Call exitFullScreen if count reaches 6
            }
        } else {
            super.onBackPressed()
        }
    }

    private fun enterFullScreen() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        fullscreenButton.visibility = View.GONE
        exitFullscreenButton.visibility = View.VISIBLE

        // Hide notifications and disable notification drawer
        mainLayout.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                )

        // Disable notifications
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_NONE)

        isFullScreen = true
    }

    private fun exitFullScreen() {
        window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        fullscreenButton.visibility = View.VISIBLE
        exitFullscreenButton.visibility = View.GONE
        mainLayout.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE

        isFullScreen = false
    }

    private fun updatePullDownCount() {
        countTextView.text = "Pull down count: $pullDownCount"
    }
}
