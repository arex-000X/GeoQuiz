package com.karaew.learning.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
private const val TAG = "MainActivity"

private val questionBank = listOf(
    Question(R.string.question_australia, true),
    Question(R.string.question_oceans, true),
    Question(R.string.question_mideast, false),
    Question(R.string.question_africa, false),
    Question(R.string.question_americas, true),
    Question(R.string.question_asia, true)
)
private var currentIndex = 0

class MainActivity : AppCompatActivity() {
    private lateinit var buttonTrue: Button
    private lateinit var buttonFalse: Button
    private lateinit var buttonNext: ImageButton
    private lateinit var buttonPrev: ImageButton
    private lateinit var questionTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)
        buttonTrue = findViewById(R.id.button_true)
        buttonFalse = findViewById(R.id.button_false)
        buttonNext = findViewById(R.id.button_next)
        buttonPrev = findViewById(R.id.button_prev)
        questionTextView = findViewById(R.id.question_text_view)
        updateQuestion()
        buttonTrue.setOnClickListener {
            checkAnswer(true)
        }
        buttonFalse.setOnClickListener {
            checkAnswer(false)
        }
        buttonNext.setOnClickListener {
            updateQuestion()
        }
        buttonPrev.setOnClickListener {
            questionBack()
        }
        questionTextView.setOnClickListener {

            updateQuestion()
        }


    }

    private fun checkAnswer(answer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer

        val messageResId = when (answer) {
            correctAnswer -> {
                R.string.correct
            }
            else -> {
                R.string.in_correct
            }
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_LONG).show()

    }
    private fun questionBack() {
        currentIndex = (currentIndex - 1) % questionBank.size
        val questionTextViewRes = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextViewRes)
        if (currentIndex == 0)  currentIndex = questionBank.size

    }


    private fun updateQuestion() {
        currentIndex = (currentIndex + 1) % questionBank.size
        val questionTextViewRes = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextViewRes)
    }
}






