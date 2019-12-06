package ar.com.nacho91.mealapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ar.com.nacho91.mealapp.data.repository.MealRepository
import ar.com.nacho91.mealapp.domain.entities.Meal
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception
import javax.inject.Inject

class MealViewModel @Inject constructor(private val repository: MealRepository) : ViewModel() {

    private var disposable: Disposable? = null

    val response = MutableLiveData<MealsResponse>()

    fun searchMeals(query: String) {

        disposable = repository.searchByName(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { response.postValue(MealsResponse.Loading) }
            .subscribe({
                if (it.isEmpty()) {
                    response.value = MealsResponse.EmptyResult
                    return@subscribe
                }

                response.value = MealsResponse.Success(it)
            },
                { response.value = MealsResponse.Error(it) })
    }

    override fun onCleared() {
        disposable?.dispose()
    }

    sealed class MealsResponse {
        object Loading : MealsResponse()
        object EmptyResult : MealsResponse()
        class Success(val meals: List<Meal>) : MealsResponse()
        class Error(val e: Throwable) : MealsResponse()
    }

}