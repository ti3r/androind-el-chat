package com.example.recipes.presentation

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elchat.APP_TAG
import com.example.recipes.cdi.RecipeRepository
import com.example.recipes.network.RecipeDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val repository: RecipeRepository,
    @Named("authToken") private val token: String
): ViewModel() {

    val recipe: MutableState<RecipeDto?> = mutableStateOf(null)
    val recipes: MutableState<List<RecipeDto>> = mutableStateOf(listOf())

    fun loadRecipes(query: String = "Chicken") {
        viewModelScope.launch {
            try{
                val results = repository.loadRecipes(query, token)
                recipes.value = results
            }catch (e: HttpException){
                Log.d(APP_TAG, "Error loading recipes. $e")
            }
        }
    }

    fun getRecipe(id: Int) {
        viewModelScope.launch {
            try {
                val result = repository.getRecipe(id, token)
                recipe.value = result
            }catch (e: HttpException){
                Log.d(APP_TAG,"Recipe not found for id $id")
            }
        }
    }

}