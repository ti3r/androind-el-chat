package com.example.recipes.cdi

import com.example.recipes.network.RecipeDtoMapper
import com.example.recipes.network.RecipeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ServiceScoped
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object NetworkModule{

    @Singleton
    @Provides
    fun providesRecipeMapper(): RecipeDtoMapper {
        return RecipeDtoMapper()
    }
}