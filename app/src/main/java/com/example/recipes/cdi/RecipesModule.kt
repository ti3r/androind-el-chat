package com.example.recipes.cdi

import com.example.recipes.network.RecipeDto
import com.example.recipes.network.RecipeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ServiceScoped
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

interface RecipeRepository {

    suspend fun loadRecipes(query:String, authToken: String): List<RecipeDto>
    suspend fun getRecipe(id: Int, authToken: String): RecipeDto
}

class MockRecipeRepository: RecipeRepository {
    override suspend fun loadRecipes(query:String, authToken: String): List<RecipeDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getRecipe(id: Int, authToken: String): RecipeDto {
        TODO("Not yet implemented")
    }
}

class SimpleRecipeRepository constructor(private val service: RecipeService): RecipeRepository {

    override suspend fun loadRecipes(query:String, authToken: String): List<RecipeDto> {
        val response = service.search(token = authToken, page = 1, query = query)
        return response.results
    }

    override suspend fun getRecipe(id: Int, authToken: String): RecipeDto {
        val recipe = service.get(authToken, id)
        val id = recipe["pk"] as Double
        val title = recipe["title"] as String
        val img = recipe["featured_image"] as String
        return RecipeDto(pk = id.toInt(), title = title, featured_image = img)
    }
}


@Module
@InstallIn(SingletonComponent::class)
class RecipesModule {

    @Singleton
    @Provides
    fun providesRecipeRepository(recipeService: RecipeService): RecipeRepository {
        return SimpleRecipeRepository(recipeService)
    }

    @Singleton
    @Provides
    @Named("authToken")
    fun providesToken(): String {
        return "Token 9c8b06d329136da358c2d00e76946b0111ce2c48"
    }

    @Singleton
    @Provides
    fun providesRecipeService(): RecipeService {
        return Retrofit.Builder().baseUrl("https://food2fork.ca/api/recipe/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RecipeService::class.java)
    }

}