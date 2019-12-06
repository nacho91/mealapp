package ar.com.nacho91.mealapp.data.repository

import ar.com.nacho91.mealapp.data.api.MealApiManager
import ar.com.nacho91.mealapp.domain.entities.Meal
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MealRepository @Inject constructor(private val apiManager: MealApiManager) {

    fun searchByName(name: String): Single<List<Meal>> {
        return apiManager.searchByName(name).map { response ->
            response.meals.map {
                Meal(
                    it.id,
                    it.name,
                    it.category,
                    it.instructions,
                    emptyList()
                )
            }
        }
    }

}