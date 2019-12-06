package ar.com.nacho91.mealapp.data.api

import ar.com.nacho91.mealapp.data.api.model.MealsResponse
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MealApiManager @Inject constructor(apiClient: ApiClient) {

    private val api = apiClient.createService(MealApi::class.java)

    fun searchByName(name: String): Single<MealsResponse> {
        return api.searchByName(name)
    }
}