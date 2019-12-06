package ar.com.nacho91.mealapp.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjection
import javax.inject.Inject

class MealActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        val model = ViewModelProviders.of(this, viewModelFactory)[MealViewModel::class.java]

        model.response.observe(this, Observer {
            when (it) {
                is MealViewModel.MealsResponse.Success -> MealAdapter(it.meals)
                is MealViewModel.MealsResponse.EmptyResult -> Toast.makeText(this, "No hay resultados", Toast.LENGTH_SHORT).show()
                is MealViewModel.MealsResponse.Loading -> Toast.makeText(this, "Cargando..", Toast.LENGTH_SHORT).show()
                is MealViewModel.MealsResponse.Error -> Toast.makeText(this, "¡Ops! Ocurrio un error", Toast.LENGTH_SHORT).show()
            }
        })

        model.searchMeals("Lasagna")
    }
}