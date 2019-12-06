package ar.com.nacho91.mealapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ar.com.nacho91.mealapp.R
import ar.com.nacho91.mealapp.domain.entities.Meal

class MealAdapter(private val meals: List<Meal>) : RecyclerView.Adapter<MealAdapter.MealHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealHolder {
        return MealHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_meal, parent, false))
    }

    override fun getItemCount(): Int = meals.size

    override fun onBindViewHolder(holder: MealHolder, position: Int) {
    }

    class MealHolder(view: View) : RecyclerView.ViewHolder(view)

}