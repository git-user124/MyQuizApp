package com.example.studyjams

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity

class ResultActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val quizTitle = findViewById<TextView>(R.id.quizTitle)
        val scoreText = findViewById<TextView>(R.id.scoreText)
        val rankingText = findViewById<TextView>(R.id.rankingText)
        val restartButton = findViewById<Button>(R.id.restartButton)

        val score = intent.getIntExtra("SCORE", 0)
        val total = intent.getIntExtra("TOTAL", 10)

        val safeTotal = total.coerceAtLeast(0)
        val safeScore = score.coerceIn(0, safeTotal)

        quizTitle.text = "🎃 Halloween Quiz Results"
        scoreText.text = "Your Score: $safeScore/$safeTotal"

        val percentage = if (safeTotal > 0) {
            safeScore * 100 / safeTotal
        } else {
            0
        }

        rankingText.text = when {
            safeTotal == 0 ->
                "The spooky challenge has no questions yet! 👻"

            safeScore == safeTotal ->
                "🏆 ABSOLUTE SPOOKY LEGEND! You aced every question!"

            percentage >= 80 ->
                "🧙 Amazing work! You're a Halloween quiz pro!"

            percentage >= 50 ->
                "🦇 Good job! You're getting the hang of the spooky stuff!"

            else ->
                "👻 The ghosts got you this time! Try again and beat your score!"
        }

        restartButton.setOnClickListener {
            val restartIntent = Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK
            }

            startActivity(restartIntent)
            finish()
        }
    }
}
