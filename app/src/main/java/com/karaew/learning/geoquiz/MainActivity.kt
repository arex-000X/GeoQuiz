package com.karaew.learning.geoquiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider

private const val TAG = "MainActivity"
private const val KEY_INDEX = "INDEX"

class MainActivity : AppCompatActivity() {
    private lateinit var buttonTrue: Button
    private lateinit var buttonFalse: Button
    private lateinit var buttonNext: Button
    private lateinit var buttonBack: Button
    private lateinit var buttonCheat: Button
    private lateinit var questionTextView: TextView
    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProvider(this).get(QuizViewModel::class.java)
    }
    val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->

            if(result.resultCode == RESULT_OK){
                quizViewModel.isCheater = result.data?.getBooleanExtra(EXTRA_ANSWER_SHOW,false) ?: false
            } else return@registerForActivityResult
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)

        val currentIndex = savedInstanceState?.getInt(KEY_INDEX, 0) ?: 0
        quizViewModel.currentIndex = currentIndex
        Log.d(TAG, "activity:${currentIndex}, and viewmodel:${quizViewModel.currentIndex}")


        buttonTrue = findViewById(R.id.button_true)
        buttonFalse = findViewById(R.id.button_false)
        buttonNext = findViewById(R.id.button_next)
        buttonBack = findViewById(R.id.button_back)
        buttonCheat = findViewById(R.id.button_cheat)
        questionTextView = findViewById(R.id.question_text_view)
        questionTextView.setText(quizViewModel.questionTextViewRes)

        buttonTrue.setOnClickListener {
            checkAnswer(true)
        }
        buttonFalse.setOnClickListener {
            checkAnswer(false)
        }
        buttonNext.setOnClickListener {
            btnNext()
        }
        buttonBack.setOnClickListener {
            btnBack()
        }
        buttonCheat.setOnClickListener {
            val answerTrue = quizViewModel.questionAnswerViewRes
            val intent = CheatActivity.newIntent(this@MainActivity, answerTrue)
            launcher.launch(intent)
        }
        questionTextView.setOnClickListener {
            btnNext()
        }


    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_INDEX, quizViewModel.currentIndex)
    }

    private fun checkAnswer(answer: Boolean) {
        val correctAnswer = quizViewModel.questionAnswerViewRes
        val messageResId = when {
            quizViewModel.isCheater -> R.string.judment_toast
            answer == correctAnswer -> R.string.correct
            else -> R.string.in_correct
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()

    }

    private fun btnBack() {
        quizViewModel.clickBack()
        questionTextView.setText(quizViewModel.questionTextViewRes)
        quizViewModel.updateCurrent()
    }

    private fun btnNext() {
        quizViewModel.clickNext()
        questionTextView.setText(quizViewModel.questionTextViewRes)
    }


}






