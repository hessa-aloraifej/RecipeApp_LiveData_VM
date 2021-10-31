package com.example.recipeapp

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface RecipeDao {
    @Query("SELECT * FROM Recipe  ORDER BY id ASC")
    fun getAllRecipe(): LiveData<List<Recipe>>

    @Delete
    fun delete(recipe:Recipe)

    @Update
    fun edit(recipe:Recipe)

    @Insert
    fun insertRecipe(recipe:Recipe)
}








