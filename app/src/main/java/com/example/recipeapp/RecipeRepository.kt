package com.example.recipeapp

import androidx.lifecycle.LiveData

class RecipeRepository (private val recipeDao: RecipeDao) {

    val getRecipes: LiveData<List<Recipe>> = recipeDao.getAllRecipe()

    suspend fun addRecipe(recipe:Recipe){
        recipeDao.insertRecipe(recipe)
    }

    suspend fun updateReci(recipe:Recipe){
        recipeDao.edit(recipe)
    }

    suspend fun deleteRecipe(recipe:Recipe){
        recipeDao.delete(recipe)
    }

}