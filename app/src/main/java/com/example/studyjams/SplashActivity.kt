
package com.example.studyjams

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity

class SplashActivity : ComponentActivity() {

    private val handler = Handler(Looper.getMainLooper())

    private val navigateToQuiz = Runnable {
        if (!isFinishing && !isDestroyed) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        window.setBackgroundDrawableResource(android.R.color.black)

        handler.postDelayed(navigateToQuiz, 3000)
    }

    override fun onDestroy() {
        handler.removeCallbacks(navigateToQuiz)
        super.onDestroy()
    }
}
