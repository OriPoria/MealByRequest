package com.example.meals.network

import com.example.meals.database.Meal
import com.example.meals.database.Meals
import com.example.testmeals.database.Categories
import com.example.testmeals.database.Category
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

interface TheMealDBApiService {

    @GET("search.php")
    fun getMeals(
        @Query("s") meal:String
    ) : Deferred<Meals>

    @GET("filter.php")
    fun getMealsFilteredByCategory(
            @Query("c") category: String
    ) : Deferred<Meals>

    @GET("categories.php")
    fun getCategories(): Deferred<Categories>

    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ) : TheMealDBApiService {
            val requestInterceptor = Interceptor { chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .build()

                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor chain.proceed(request)
            }
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor).addInterceptor(connectivityInterceptor)
                .build()
            return Retrofit.Builder().client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .baseUrl(BASE_URL)
                .build()
                .create(TheMealDBApiService::class.java)
        }
    }
}