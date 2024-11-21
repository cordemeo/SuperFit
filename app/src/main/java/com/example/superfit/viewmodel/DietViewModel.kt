package com.example.superfit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superfit.network.CalorieRequest
import com.example.superfit.network.CalorieResponse
import com.example.superfit.network.RetrofitClient
import kotlinx.coroutines.launch
import retrofit2.Response

class DietViewModel : ViewModel() {

    private val _calorieResult = MutableLiveData<Int?>()
    val calorieResult: LiveData<Int?> get() = _calorieResult

    private val _totalCalories = MutableLiveData<Int>(0)
    val totalCalories: LiveData<Int> get() = _totalCalories

    fun fetchCaloriesForDish(dishName: String) {
        viewModelScope.launch {
            try {
                val response: Response<CalorieResponse> = RetrofitClient.apiService.fetchCalories(
                    CalorieRequest(dishName)
                ).execute()

                if (response.isSuccessful && response.body() != null) {
                    _calorieResult.postValue(response.body()?.calories)
                } else {
                    _calorieResult.postValue(null)
                }
            } catch (e: Exception) {
                _calorieResult.postValue(null)
            }
        }
    }

    fun addCalories(calories: Int) {
        _totalCalories.value = (_totalCalories.value ?: 0) + calories
    }

    fun resetCalories() {
        _totalCalories.value = 0
    }
}
