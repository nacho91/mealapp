package ar.com.nacho91.mealapp.domain.entities

import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import java.lang.IllegalStateException

class MealTest {

    @get:Rule
    var thrown: ExpectedException = ExpectedException.none()

    @Test
    fun meal_WithZeroIngredients_GetLevel_ThrowException() {

        //arrange
        val meal = Meal("4er",
            "Fruta",
            "Otra fruta",
            "Mas fruta",
            emptyList())

        thrown.expect(IllegalStateException::class.java)
        thrown.expectMessage("Invalid ingredients size")

        //act
        meal.getLevel()
    }

    @Test
    fun meal_With6Ingredients_GetLevel_IsEasy() {

        //arrange
        val ingredients = arrayListOf(Ingredient("", ""),
            Ingredient("", ""),
            Ingredient("", ""),
            Ingredient("", ""),
            Ingredient("", ""),
            Ingredient("", ""))

        val meal = Meal("4er",
            "Fruta",
            "Otra fruta",
            "Mas fruta",
            ingredients)

        //act
        val level = meal.getLevel()

        //assert
        assertEquals(MealLevel.EASY, level)
    }

}