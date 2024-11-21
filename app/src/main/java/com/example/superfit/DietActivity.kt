package com.example.superfit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.superfit.meals.BreakfastActivity
import com.example.superfit.meals.DinnerActivity
import com.example.superfit.meals.LunchActivity
import com.example.superfit.meals.SnackActivity

class DietActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diet)

        val breakfastButton = findViewById<Button>(R.id.buttonBreakfast)
        val lunchButton = findViewById<Button>(R.id.buttonLunch)
        val snackButton = findViewById<Button>(R.id.buttonSnack)
        val dinnerButton = findViewById<Button>(R.id.buttonDinner)

        breakfastButton.setOnClickListener {
            startActivity(Intent(this, BreakfastActivity::class.java))
        }

        lunchButton.setOnClickListener {
            startActivity(Intent(this, LunchActivity::class.java))
        }

        snackButton.setOnClickListener {
            startActivity(Intent(this, SnackActivity::class.java))
        }

        dinnerButton.setOnClickListener {
            startActivity(Intent(this, DinnerActivity::class.java))
        }
    }
}