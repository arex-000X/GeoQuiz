package com.karaew.learning.geoquiz

import android.util.Log
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"

class QuizViewModel : ViewModel() {
    var isCheater = false
    private val questionBank = listOf(
        Question(R.string.question_australia, true, false),
        Question(R.string.question_oceans, true, false),
        Question(R.string.question_mideast, false, false),
        Question(R.string.question_africa, false, false),
        Question(R.string.question_americas, true, false),
        Question(R.string.question_asia, true, false)
    )

    var currentIndex = 0

    val questionAnswerViewRes: Boolean
        get() = questionBank[currentIndex].answer
    val questionTextViewRes: Int
        get() = questionBank[currentIndex].textResId
    var questionStatusCheats: Boolean = false
        get() = questionBank[currentIndex].statusCheat

    fun clickNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
        Log.d(TAG, "Button click Next   List item: ${currentIndex}")
        Log.d(TAG, "Button click Next   List item: ${questionBank}")
    }

    fun clickBack() {
        currentIndex = (currentIndex - 1) % questionBank.size
        Log.d(TAG, "Button click Back  List item: ${currentIndex}")

    }

    fun updateCurrent() {
        if (currentIndex == 0) currentIndex = questionBank.size
    }


}




