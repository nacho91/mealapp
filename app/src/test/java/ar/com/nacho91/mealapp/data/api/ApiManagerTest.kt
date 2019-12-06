package ar.com.nacho91.mealapp.data.api

import ar.com.nacho91.mealapp.util.ResourcesHelper
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ApiManagerTest {

    private var mockWebServer = MockWebServer()

    private lateinit var apiManager: MealApiManager

    @Before
    fun setup() {
        mockWebServer.start()

        val url = mockWebServer.url("/").toString()

        apiManager = MealApiManager(ApiClient(url.substring(0, url.length - 1)))
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun apiManager_MealsSearchSuccess_SearchByName_IsOk() {

        //arrange
        val response = MockResponse()
            .setResponseCode(200)
            .setBody(ResourcesHelper.getJson("json/meals_response.json"))

        mockWebServer.enqueue(response)

        //act
        val mealsResponse = apiManager.searchByName("pepito").blockingGet()

        //assert
        val request = mockWebServer.takeRequest()

        assertEquals("GET", request.method)
        assertEquals("/search.php?s=pepito", request.path)
    }

}