package com.karaew.learning.geoquiz

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"

class QuizViewModel : ViewModel() {

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    var currentIndex = 0
    val questionAnswerViewRes: Boolean
        get() = questionBank[currentIndex].answer
    val questionTextViewRes: Int
        get() = questionBank[currentIndex].textResId

    fun clickNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
        Log.d(TAG, "Button click Next   List item: ${currentIndex}")
    }

    fun clickBack() {
        currentIndex = (currentIndex - 1) % questionBank.size
        Log.d(TAG, "Button click Back  List item: ${currentIndex}")
    }

    fun updateCurrent() {
        if (currentIndex == 0) currentIndex = questionBank.size
    }
}




