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
        "I have a spine, but no bones. I tell stories, but have no voice. What am I?",
        "The person who makes it has no need of it; the person who buys it has no use for it. What am I?",
        "I pull you down but never touch you. Without me, you'd fly away into nothingness. What am I?",
        "I wear a mask, sneak around your code, and steal your memory assets. What am I?",
        "What do cute little ghosts use to clean their hair?",
        "Which sweet treat was originally called 'Chicken Feed'?",
        "What is a vampire's favorite pop-culture fruit?",
        "How do you fix a cracked jack-o'-lantern pumpkin?",
        "I look at you, you look at me. I raise my right hand, you raise your left. What am I?",
        "I have keys but open no locks. I have space but no room. What am I?"
    )

    private val options = arrayOf(
        arrayOf("A skeleton", "A haunted book", "A custom list", "A local map"),
        arrayOf("A tombstone", "A candy piece", "A wooden coffin", "A scary mask"),
        arrayOf("A dark ghost", "Gravity force", "A layout view", "A background loop"),
        arrayOf("A runtime bug", "A local socket", "An intent filter", "A security leak"),
        arrayOf("Scare spray", "Sham-boo!", "Spooky soap", "Ghost mist"),
        arrayOf("Candy Corn", "Gummy Bears", "Jelly Beans", "Chocolate"),
        arrayOf("A blood orange", "A scary berry", "A neck-tarine", "A boo-nana"),
        arrayOf("With a pumpkin patch!", "With monster glue", "With spooky tape", "With ghost thread"),
        arrayOf("A real ghost", "A clean mirror", "A shadow form", "A canvas drawing"),
        arrayOf("A piano view", "A system box", "A storage path", "A keyboard panel")
    )

    private val correctAnswers = intArrayOf(1, 2, 1, 0, 1, 0, 2, 0, 1, 3)
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
            if (!answered) return@setOnClickListener
            
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
        questionNumberText.text = "🕯️ Spooky Level: ${currentQuestion + 1}/${questions.size}"

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
