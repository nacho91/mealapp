package ar.com.nacho91.mealapp.data.api

import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    @GET("search.php")
    fun searchByName(@Query("s") query: String)

    @GET("categories.php")
    fun categories()
}