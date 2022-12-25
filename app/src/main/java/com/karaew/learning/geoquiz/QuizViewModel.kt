package com.karaew.learning.geoquiz

import android.util.Log
import androidx.annotation.LongDef
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"


class QuizViewModel : ViewModel() {

    private val questionBank = listOf(
        Question(R.string.question_australia, true, false),
        Question(R.string.question_oceans, true, false),
        Question(R.string.question_mideast, false, false),
        Question(R.string.question_africa, false, false),
        Question(R.string.question_americas, true, false),
        Question(R.string.question_asia, true, false)
    )

    var currentIndex = 0
    var isCheater = false
    var numberOfAttempts = 3
    val questionAnswerViewRes: Boolean
        get() = questionBank[currentIndex].answer
    val questionTextViewRes: Int
        get() = questionBank[currentIndex].textResId
    val questionStatusCheck: Boolean get() = questionBank[currentIndex].statusCheat

    fun clickNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
        Log.d(TAG,currentIndex.toString())
    }

    fun clickBack() {
        currentIndex = (currentIndex  - 1) % questionBank.size
        Log.d(TAG,currentIndex.toString())
    }

    fun updateQuestion(){
        if(currentIndex == 0) currentIndex = questionBank.size
    }

 fun checkStatus(){
      questionBank[currentIndex].statusCheat = isCheater

 }



}








