package com.example.studyjams

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.studyjams.R

class MainActivity : ComponentActivity() {

    private lateinit var questionText : TextView
    private lateinit var questionNumberText : TextView
    private lateinit var optionsGroup : RadioGroup
    private lateinit var nextButton : Button

    private val questions = arrayOf(
        "What do cute little ghosts use to wash their hair?",
        "Which sweet treat was originally called 'Chicken Feed'?",
        "What is a vampire's favorite cartoon fruit?",
        "How do you fix a cracked jack-o'-lantern pumpkin?"
    )

    private val options = arrayOf(
        arrayOf("Scare spray", "Sham-boo!", "Spooky soap", "Ghost mist"),
        arrayOf("Candy Corn", "Gummy Bears", "Jelly Beans", "Chocolate Kisses"),
        arrayOf("A blood orange", "A scary berry", "A neck-tarine", "A boo-nana"),
        arrayOf("With a pumpkin patch!", "With monster glue", "With spooky tape", "With ghost thread")
    )

    private val correctAnswers = intArrayOf(
        1, 0, 2, 0
    )
    private var currentQuestion = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        questionText = findViewById(R.id.questionText)
        questionNumberText = findViewById(R.id.questionNumberText)
        optionsGroup = findViewById(R.id.optionsGroup)
        nextButton = findViewById(R.id.nextButton)

        showQuestion()

        nextButton.setOnClickListener {
            val answered = checkAnswer()
            if (!answered) {
                return@setOnClickListener
            }
            
            if (currentQuestion < questions.size - 1) {
                currentQuestion++
                showQuestion()
            } else {
                openResultScreen()
            }
        }
    }

    private fun showQuestion() {
        questionText.text = questions[currentQuestion]
        questionNumberText.text = "🎃 Spooky Level: ${currentQuestion + 1}/${questions.size}"

        val radioButtons = arrayOf(
            findViewById<RadioButton>(R.id.option1),
            findViewById<RadioButton>(R.id.option2),
            findViewById<RadioButton>(R.id.option3),
            findViewById<RadioButton>(R.id.option4)
        )

        for (i in radioButtons.indices) {
            radioButtons[i].text = options[currentQuestion][i]
        }
        optionsGroup.clearCheck()
    }

    private fun checkAnswer() : Boolean {
        val selectedId = optionsGroup.checkedRadioButtonId
        if (selectedId == -1) {
            Toast.makeText(this, "Pick an answer, don't be scared! 👻", Toast.LENGTH_SHORT).show()
            return false
        }
        
        val selectedButton = findViewById<RadioButton>(selectedId)
        val selectedIndex = options[currentQuestion].indexOf(selectedButton.text.toString())
        
        if (selectedIndex == correctAnswers[currentQuestion]) {
            score++
            Toast.makeText(this, "🍬 Sweet! Correct!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "🔮 Silly ghost! Try harder next time!", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    private fun openResultScreen() {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("SCORE", score)
        intent.putExtra("TOTAL", questions.size)
        startActivity(intent)
        finish()
    }
}
