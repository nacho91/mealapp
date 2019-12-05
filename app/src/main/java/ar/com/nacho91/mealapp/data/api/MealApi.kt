package ar.com.nacho91.mealapp.data.api

import ar.com.nacho91.mealapp.data.api.model.MealsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    @GET("search.php")
    fun searchByName(@Query("s") query: String): Single<MealsResponse>

    @GET("categories.php")
    fun categories()
}