package com.example.carapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth


class Hard : AppCompatActivity(), GameTask {
    private lateinit var rootLayout: LinearLayout
    private lateinit var startBtn: Button
    private lateinit var mGameView: GameView3
    private lateinit var score: TextView
    private lateinit var pauseBtn: ImageView
    private lateinit var resumeBtn: ImageView
    private lateinit var logoutBtn: Button
    private lateinit var backButton: Button

    private val backgrounds = Backgrounds()
    private lateinit var backgroundButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Check if the user is logged in
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser == null) {
            // User is not logged in, start the Login activity
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish() // Finish the MainActivity to prevent going back to it after login
        } else {
            // User is logged in, proceed to the game page
            setContentView(R.layout.activity_main)
            setupGamePage()
            backgroundButton = findViewById(R.id.backgroundButton)
            backgroundButton.setOnClickListener {
                updateBackground()
            }
            val instructionsButton: ImageView = findViewById(R.id.instructionsButton)
            instructionsButton.setOnClickListener {
                val intent = Intent(this, instructions::class.java)
                startActivity(intent)
            }

            pauseBtn = findViewById(R.id.pauseBtn)
            resumeBtn = findViewById(R.id.resumeBtn)
            logoutBtn = findViewById(R.id.logoutBtn)

            pauseBtn.setOnClickListener {
                pauseGame()
            }

            resumeBtn.setOnClickListener {
                resumeGame()
            }

            logoutBtn.setOnClickListener {
                logoutUser()
            }
            backButton = findViewById(R.id.backButton)
            backButton.setOnClickListener {
                onBackPressed()
            }

        }
    }

    private fun setupGamePage() {
        startBtn = findViewById(R.id.startBtn)
        rootLayout = findViewById(R.id.rootLayout)
        score = findViewById(R.id.score)
        mGameView = GameView3(this, this)

        startBtn.setOnClickListener {
            startGame()
        }
    }

    private fun startGame() {
        updateBackground()
        rootLayout.addView(mGameView,LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        startBtn.visibility = View.GONE
        score.visibility = View.GONE
        backgroundButton.visibility = View.VISIBLE
        pauseBtn.visibility = View.VISIBLE
        resumeBtn.visibility = View.VISIBLE
        logoutBtn.visibility = View.GONE
        backButton.visibility=View.GONE
        backgroundButton.setOnClickListener(){
            updateBackground()
        }
    }

    private fun updateBackground() {
        val background = backgrounds.getBackground()
        mGameView.setBackgroundResource(background)
    }

    override fun closeGame(mScore: Int) {
        score.text = "Score: $mScore"
        rootLayout.removeView(mGameView)
        startBtn.visibility = View.VISIBLE
        score.visibility = View.VISIBLE
        backgroundButton.visibility = View.VISIBLE
        pauseBtn.visibility = View.GONE
        resumeBtn.visibility = View.GONE
        backButton.visibility = View.VISIBLE
        logoutBtn.visibility = View.VISIBLE
        setupGamePage()
    }

    private fun pauseGame() {
        mGameView.pauseGame()
    }

    private fun resumeGame() {
        mGameView.resumeGame()
    }

    private fun logoutUser() {
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        finish()
    }

}