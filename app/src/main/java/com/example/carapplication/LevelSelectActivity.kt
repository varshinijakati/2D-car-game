package com.example.carapplication
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.carapplication.Normal
import com.example.carapplication.R

class LevelSelectActivity : AppCompatActivity() {

    private lateinit var easyButton: Button
    private lateinit var mediumButton: Button
    private lateinit var hardButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.levelselect)

        easyButton = findViewById(R.id.easyButton)
        mediumButton = findViewById(R.id.mediumButton)
        hardButton = findViewById(R.id.hardButton)

        easyButton.setOnClickListener {
            startGameWithLevelEasy("Easy")
        }

        mediumButton.setOnClickListener {
            startGameWithLevelMedium("Medium")
        }

        hardButton.setOnClickListener {
            startGameWithLevelHard("Hard")
        }
    }

    private fun startGameWithLevelEasy(level: String) {
        // Start the game activity and pass the selected level
        val intent = Intent(this, Easy::class.java)
        intent.putExtra("level", level)
        startActivity(intent)
    }

    private fun startGameWithLevelMedium(level: String) {
        // Start the game activity and pass the selected level
        val intent = Intent(this, Normal::class.java)
        intent.putExtra("level", level)
        startActivity(intent)
    }

    private fun startGameWithLevelHard(level: String) {
        // Start the game activity and pass the selected level
        val intent = Intent(this, Hard::class.java)
        intent.putExtra("level", level)
        startActivity(intent)
    }
}
