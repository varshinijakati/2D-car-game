package com.example.carapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.carapplication.databinding.FirstpageBinding

class firstpage : AppCompatActivity() {
    private lateinit var binding: FirstpageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FirstpageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.STARTButton.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
            finish()
        }
    }
}

