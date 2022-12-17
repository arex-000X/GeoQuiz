package com.karaew.learning.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
private const val EXTRA_ANSWER_SHOW ="com.karaew.learning.geoquiz.answer_is_show"
private const val EXTRS_ANSWER_TRUE = "com.karaew.learning.geoquiz.answer_is_true"

class CheatActivity : AppCompatActivity() {
    private lateinit var answerTextView: TextView
    private lateinit var buttonShowAnswer: Button
    private var answerIsTrue = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)
        answerTextView = findViewById(R.id.answer_text)
        buttonShowAnswer = findViewById(R.id.button_show_answer)
        answerIsTrue = intent.getBooleanExtra(EXTRS_ANSWER_TRUE, false)

        buttonShowAnswer.setOnClickListener {
            val answerText = when {
                answerIsTrue -> R.string.btn_true
                else -> R.string.btn_false
            }
            answerTextView.setText(answerText)
            setAnswerShowResult(true)
        }
    }

    private fun setAnswerShowResult(isAnswerShow:Boolean){
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOW,isAnswerShow)
        }
        setResult(Activity.RESULT_OK,data)
    }
    companion object {
        fun newIntent(packageName: Context, answerTrue: Boolean): Intent {

            return Intent(packageName, CheatActivity::class.java).apply {
                putExtra(EXTRS_ANSWER_TRUE, answerTrue)
            }
        }
    }
}