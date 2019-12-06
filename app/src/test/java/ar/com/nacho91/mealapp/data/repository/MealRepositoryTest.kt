package ar.com.nacho91.mealapp.data.repository

import ar.com.nacho91.mealapp.data.api.MealApiManager
import ar.com.nacho91.mealapp.data.api.model.MealsResponse
import ar.com.nacho91.mealapp.domain.entities.Meal
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.any
import org.mockito.MockitoAnnotations
import java.lang.Exception

class MealRepositoryTest {

    @Mock
    private lateinit var apiManager: MealApiManager

    private lateinit var repository: MealRepository

    @Before
    fun setup() {

        MockitoAnnotations.initMocks(this)

        repository = MealRepository(apiManager)
    }

    @Test
    fun mealRepository_ApiManagerSuccess_SearchByName_IsOk() {

        //arrange
        val exception = Exception()

        `when`(apiManager.searchByName(ArgumentMatchers.anyString()))
            .thenReturn(Single.error(exception))

        val testObserver = TestObserver<List<Meal>>()

        //act
        repository.searchByName("pepe").subscribe(testObserver)

        //assert
        testObserver.assertError(exception)
    }


}