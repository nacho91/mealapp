package ar.com.nacho91.mealapp.domain.entities

class Meal(
    val id: String,
    val name: String,
    val category: String,
    val instructions: String,
    val ingredients: List<Ingredient>
) {

    fun getLevel(): MealLevel {
        return when (ingredients.size) {
            in 1..6 -> MealLevel.EASY
            in 6..10 -> MealLevel.NORMAL
            in 10..16 -> MealLevel.ADVANCE
            else -> MealLevel.EXPERT
        }
    }
}