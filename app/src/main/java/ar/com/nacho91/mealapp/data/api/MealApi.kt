package ar.com.nacho91.mealapp.data.api

interface MealApi {

    fun searchByName(query: String)

    fun categories()
}