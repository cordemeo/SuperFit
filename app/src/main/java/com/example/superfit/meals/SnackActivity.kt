package com.example.superfit.meals

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.superfit.R
import com.example.superfit.viewmodel.DietViewModel

class SnackActivity : AppCompatActivity() {

    private val viewModel: DietViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snack)

        val dishInput = findViewById<EditText>(R.id.editTextDish)
        val fetchButton = findViewById<Button>(R.id.buttonFetchCalories)
        val resultText = findViewById<TextView>(R.id.textViewCalories)
        val totalText = findViewById<TextView>(R.id.textViewTotalCalories)
        val addToMealButton = findViewById<Button>(R.id.buttonAddToMeal)
        val calculateButton = findViewById<Button>(R.id.buttonCalculate)

        fetchButton.setOnClickListener {
            val dish = dishInput.text.toString()
            if (dish.isNotEmpty()) {
                viewModel.fetchCaloriesForDish(dish)
            }
        }

        addToMealButton.setOnClickListener {
            viewModel.calorieResult.value?.let { calories ->
                viewModel.addCalories(calories)
            }
        }

        viewModel.calorieResult.observe(this) { calories ->
            resultText.text = "Калорийность блюда: ${calories ?: "Ошибка"}"
        }

        viewModel.totalCalories.observe(this) { total ->
            totalText.text = "Общая калорийность: $total"
        }

        calculateButton.setOnClickListener {
            val recommended = 300
            val total = viewModel.totalCalories.value ?: 0
            val diff = (total - recommended).toFloat() / recommended

            val message = when {
                diff > 0.15 -> "Превышение нормы на ${"%.1f".format(diff * 100)}%!"
                diff < -0.15 -> "Недобор на ${"%.1f".format(-diff * 100)}%!"
                else -> "Рацион сбалансирован."
            }
            totalText.text = message
        }
    }
}
