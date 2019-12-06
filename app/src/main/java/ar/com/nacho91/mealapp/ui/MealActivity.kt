package ar.com.nacho91.mealapp.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.com.nacho91.mealapp.R
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_meal.view.*
import javax.inject.Inject

class MealActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val mealList by lazy { findViewById<RecyclerView>(R.id.meal_list) }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal)

        mealList.layoutManager = LinearLayoutManager(this)

        val model = ViewModelProviders.of(this, viewModelFactory)[MealViewModel::class.java]

        model.response.observe(this, Observer {
            when (it) {
                is MealViewModel.MealsResponse.Success -> mealList.adapter = MealAdapter(it.meals)
                is MealViewModel.MealsResponse.EmptyResult -> Toast.makeText(this, "No hay resultados", Toast.LENGTH_SHORT).show()
                is MealViewModel.MealsResponse.Loading -> Toast.makeText(this, "Cargando..", Toast.LENGTH_SHORT).show()
                is MealViewModel.MealsResponse.Error -> Toast.makeText(this, "¡Ops! Ocurrio un error", Toast.LENGTH_SHORT).show()
            }
        })

        model.searchMeals("Lasagna")
    }
}