package com.example.carapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.carapplication.R

class instructions : AppCompatActivity() {

    private lateinit var backBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.instructions)

        backBtn = findViewById(R.id.backBtn)
        backBtn.setOnClickListener {
            // Handle back button click
            finish() // Close the instructions activity and go back to the previous activity (main activity)
        }
    }
}