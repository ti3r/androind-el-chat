package com.example.recipes.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import java.util.*

data class RecipeSearchResponse(val count: Int,
    val results: List<RecipeDto>)

@Parcelize
data class RecipeDto(val pk: Int? = null,
                     val title: String? = null,
                    val featured_image: String?): Parcelable


interface RecipeService {

    @GET("search")
    suspend fun search(
        @Header("Authorization") token: String,
        @Query("page") page: Int,
        @Query("query") query: String): RecipeSearchResponse

    @GET("get")
    suspend fun get(@Header("Authorization") token: String,
                    @Query("id") id: Int): Map<String, Object>


}