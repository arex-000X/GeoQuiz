package com.karaew.learning.geoquiz

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.Toast

private lateinit var buttonTrue: Button
private lateinit var buttonFalse: Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonTrue = findViewById(R.id.button_true)
        buttonFalse = findViewById(R.id.button_false)

        buttonTrue.setOnClickListener {
            val toast = Toast.makeText(this, R.string.correct, Toast.LENGTH_LONG)
            toast.show()
        }
        buttonFalse.setOnClickListener {
            val toast = Toast.makeText(this, R.string.in_correct, Toast.LENGTH_LONG)
            toast.show()
        }


    }
}