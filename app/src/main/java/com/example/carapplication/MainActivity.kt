package com.example.carapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.startactivity)



        val startBtn = findViewById<Button>(R.id.startGameBtn)
        startBtn.setOnClickListener {
            startGame()
        }
    }

    private fun startGame() {
        startLevelSelection()
    }

    private fun startLevelSelection() {
        val intent = Intent(this, LevelSelectActivity::class.java)
        startActivity(intent)
    }
}
