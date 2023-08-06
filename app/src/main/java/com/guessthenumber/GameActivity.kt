package com.guessthenumber

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import kotlinx.android.synthetic.main.activity_game.*
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    private var secretNumber: Int = 0
    private var attempts: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        clickButton()
    }

    private fun clickButton() {
        secretNumber = Random.nextInt(1, 101)
        val alphaAnimation = AlphaAnimation(1.0f, 0.0f)
        alphaAnimation.duration = 1500
        checkButton.setOnClickListener {
            val input = number_edit.text.toString().toIntOrNull()
            checkButton.startAnimation(alphaAnimation)
            if (input != null) {
                attempts++
                if (input == secretNumber) {
                    val message =
                        getString(R.string.congratulations_message, secretNumber, attempts)
                    result_text.text = message
                    resultButton.visibility = View.VISIBLE
                    checkButton.isEnabled = false
                } else if (input < secretNumber) {
                    result_text.text = getString(R.string.greater_number_message)
                } else {
                    result_text.text = getString(R.string.smaller_number_message)
                }
            } else {
                result_text.text = getString(R.string.enter_number_message)
            }
        }

        resultButton.setOnClickListener {
            resultButton.startAnimation(alphaAnimation)
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("attempts", attempts)
            startActivity(intent)
        }
    }

    @Deprecated(
        "Deprecated in Java",
        ReplaceWith("super.onBackPressed()", "androidx.appcompat.app.AppCompatActivity")
    )
    override fun onBackPressed() {
        super.onBackPressed()
    }
}