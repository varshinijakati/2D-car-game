package com.example.carapplication

import kotlin.random.Random

class Backgrounds {
    private val backgrounds = arrayOf(

        R.drawable.bg1,
        R.drawable.bg2,
        R.drawable.bg3,
        R.drawable.road
    )

    fun getBackground(): Int {
        val randomIndex = (0 until backgrounds.size).random()
        return backgrounds[Random.nextInt(backgrounds.size)]
        }
}
