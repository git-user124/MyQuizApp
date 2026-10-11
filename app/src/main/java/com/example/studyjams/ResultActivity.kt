package com.example.studyjams

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.studyjams.R

class ResultActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val score = intent.getIntExtra("SCORE", 0)
        val total = intent.getIntExtra("TOTAL", 10)

        val scoreText = findViewById<TextView>(R.id.scoreText)
        val rankText = findViewById<TextView>(R.id.rankText)
        val restartButton = findViewById<Button>(R.id.restartButton)

        scoreText.text = "🍬 You collected $score out of $total treats! 🎃"

        // 🧠 Pro-Level Ranking Logic Evaluation System
        if (score >= 8) {
            rankText.text = "⚡ RANK: IMMORTAL SPOOKY PRO! 💀\n(You mastered every dark puzzle perfectly!)"
        } else if (score >= 5) {
            rankText.text = "🔮 RANK: SURVIVED APPRENTICE! 🧟\n(Good effort, you escaped the graveyard layout!)"
        } else {
            rankText.text = "🦇 RANK: GRAVEYARD FOOD! 🪦\n(The bugs caught you! Try again!)"
        }

        restartButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
