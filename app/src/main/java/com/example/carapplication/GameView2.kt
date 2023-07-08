package com.example.carapplication

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View

class GameView2(var c: Context, var gameTask: GameTask) : View(c) {
    private var myPaint: Paint? = null
    private var speed2 = 2
    //private var speed2 = 1
   // private var speed3 = 1
    private var time = 0
    private var score = 0
    private var myCarPosition = 0
    private val otherCars = ArrayList<HashMap<String, Any>>()
    private var isPaused = false

    var viewWidth = 0
    var viewHeight = 0

    init {
        myPaint = Paint()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        viewWidth = this.measuredWidth
        viewHeight = this.measuredHeight

        if (!isPaused) {
            if (time % 700 < 10 + speed2) {
                val map = HashMap<String, Any>()
                map["lane"] = (0..2).random()
                map["startTime"] = time
                otherCars.add(map)
            }
            time = time + 10 + speed2
        }

        val carWidth = viewWidth / 5
        val carHeight = carWidth + 10
        myPaint!!.style = Paint.Style.FILL

        // Draw my car
        val myCarDrawable = resources.getDrawable(R.drawable.cars_reds, null)
        myCarDrawable.setBounds(
            myCarPosition * viewWidth / 3 + viewWidth / 15 + 25,
            viewHeight - 2 - carHeight,
            myCarPosition * viewWidth / 3 + viewWidth / 15 + carWidth - 25,
            viewHeight - 2
        )
        myCarDrawable.draw(canvas!!)

        // Draw other cars
        val carType1Drawable = resources.getDrawable(R.drawable.cars_yellows, null)
        for (i in otherCars.indices) {
            try {
                val carX = otherCars[i]["lane"] as Int * viewWidth / 3 + viewWidth / 15
                var carY = time - otherCars[i]["startTime"] as Int

                val drawable = carType1Drawable

                drawable.setBounds(
                    carX + 25, carY - carHeight, carX + carWidth - 25, carY
                )
                drawable.draw(canvas)

                if (otherCars[i]["lane"] as Int == myCarPosition) {
                    if (carY > viewHeight - 2 - carHeight && carY < viewHeight - 2) {
                        gameTask.closeGame(score)
                    }
                }

                if (carY > viewHeight + carHeight) {
                    otherCars.removeAt(i)
                    score++
                    speed2 = 2 + Math.abs(score / 6)
                   // speed2 = 2 + Math.abs(score / 8)
                   // speed3 = 4 + Math.abs(score / 6)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        myPaint!!.color = Color.WHITE
        myPaint!!.textSize = 40f
        canvas.drawText("Score: $score", 80f, 80f, myPaint!!)
        canvas.drawText("Speed: $speed2", 380f, 80f, myPaint!!)
        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (!isPaused) {
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    val x1 = event.x
                    if (x1 < viewWidth / 2) {
                        if (myCarPosition > 0) {
                            myCarPosition--
                        }
                    }
                    if (x1 > viewWidth / 2) {
                        if (myCarPosition < 2) {
                            myCarPosition++
                        }
                    }
                    invalidate()
                }
                MotionEvent.ACTION_UP -> {
                }
            }
        }
        return true
    }

    fun pauseGame() {
        isPaused = true
    }

    fun resumeGame() {
        isPaused = false
    }
}
