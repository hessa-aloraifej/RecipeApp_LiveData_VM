package com.example.recipeapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class MyVM (application: Application): AndroidViewModel(application) {
   var db= RecipeDatabase.getInstance(application)
   private val repository: RecipeRepository
   private val recipes: LiveData<List<Recipe>>

   init {
      val recipeDao = RecipeDatabase.getInstance(application).RecipeDao()
      repository = RecipeRepository(recipeDao)
      recipes = repository.getRecipes
   }

   fun getRecipes(): LiveData<List<Recipe>> {
      return recipes
   }

   fun add(s:Recipe) {
      //
      db.RecipeDao().insertRecipe(s)
      //}

      //Toast.makeText(application, "data saved successfully! ", Toast.LENGTH_SHORT)
      // .show();
   }

   fun remove(recipe:Recipe){
      db.RecipeDao().delete(recipe)


   }
   fun edit(recipe:Recipe){
      db.RecipeDao().edit(recipe)


   }





}