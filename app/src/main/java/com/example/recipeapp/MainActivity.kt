package com.example.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private val vm by lazy { ViewModelProvider(this).get(MyVM::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewBtn=findViewById<Button>(R.id.viewBtn)
        val addbtn=findViewById<Button>(R.id.addusserBtn)
        val editTextTitle=findViewById<EditText>(R.id.editTextTitle)
        val editTextAuthor=findViewById<EditText>(R.id.editTextAuthor)
        val editTextIngredients=findViewById<EditText>(R.id.editTextIngredients)
        val editTextInstructions=findViewById<EditText>(R.id.editTextInstructions)



        addbtn.setOnClickListener {
            var title=editTextTitle.text.toString()
            var author=editTextAuthor.text.toString()
            var ingredients=editTextIngredients.text.toString()
            var instructions=editTextInstructions.text.toString()
            var recipe = Recipe(0, title,author,ingredients,instructions)
         if(title.isNotEmpty()&&author.isNotEmpty()&&ingredients.isNotEmpty()&&instructions.isNotEmpty()) {

            // CoroutineScope(IO).launch {
                // RecipeDatabase.getInstance(applicationContext).RecipeDao().insertRecipe(recipe)
                vm.add(recipe)
             //}
             Toast.makeText(applicationContext, "data saved successfully! ", Toast.LENGTH_SHORT)
                 .show();
             editTextTitle.text.clear()
             editTextAuthor.text.clear()
             editTextIngredients.text.clear()
             editTextInstructions.text.clear()
         }
            else{
             Toast.makeText(applicationContext, "You Should Complete The Entries! ", Toast.LENGTH_SHORT)
                 .show();
            }

        }
        viewBtn.setOnClickListener {
            val intent = Intent(this, ViewRecipe::class.java)
            startActivity(intent)
        }

    }
}