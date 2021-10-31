package com.example.recipeapp

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ViewRecipe : AppCompatActivity() {
    private val vm by lazy { ViewModelProvider(this).get(MyVM::class.java) }

    lateinit var mainRV: RecyclerView
   // lateinit var list:List<Recipe>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_recipe)

        var addBtn=findViewById<Button>(R.id.addUsersbtn)
       mainRV = findViewById(R.id.rvMain)

        vm.getRecipes().observe(this, {
                recipes ->  mainRV.adapter = RecipeRVAdapter(this,recipes)
            mainRV.layoutManager = LinearLayoutManager(this)

        })

     //   CoroutineScope(IO).launch {
        //            list = RecipeDatabase.getInstance(applicationContext).RecipeDao().getAllRecipe()
        //            withContext(Dispatchers.Main){
        //                updateView(list)
        //            }
        //        }

        addBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }



    }



    fun editAlert(idRecipe: Int , title: String, author: String, ingredients: String,instructions: String) {

        val builder = AlertDialog.Builder(this)

        val view = layoutInflater.inflate(R.layout.edit_alert, null)

        builder.setView(view)

        val alertDialog: AlertDialog = builder.create()

        val etTitle = view.findViewById<EditText>(R.id.etTitle)
        val etAuthor = view.findViewById<EditText>(R.id.etAuthor)
        val etIngredients = view.findViewById<EditText>(R.id.etIngredients)
        val etInstructions = view.findViewById<EditText>(R.id.etInstructions)
        val edit = view.findViewById<Button>(R.id.edit)

        etTitle.setText(title)
        etAuthor.setText(author)
        etIngredients.setText(ingredients)
        etInstructions.setText(instructions)

        alertDialog.show()


        edit.setOnClickListener {
            var utitle = etTitle.text.toString()
            var uauthor = etAuthor.text.toString()
            var uingredients = etIngredients.text.toString()
            var uinstructions = etInstructions.text.toString()
            etTitle.hint="Enter The Title"
            etAuthor.hint="Enter The Author"
            etIngredients.hint="Enter The Ingredients"
            etInstructions.hint="Enter The Instructions"
            var receipObj = Recipe(idRecipe, utitle, uauthor, uingredients, uinstructions)
            vm.edit(receipObj)
            etTitle.text.clear()
            etAuthor.text.clear()
            etIngredients.text.clear()
            etInstructions.text.clear()
            alertDialog.dismiss()

        }



        }
    fun confirmAlert(recipe: Recipe){


        val dialogBuilder = AlertDialog.Builder(this)

        dialogBuilder.setMessage("Are You Sure To Delete This Recipe?")
            .setPositiveButton("Yes", DialogInterface.OnClickListener {
                    dialog, id ->
                vm.remove(recipe)
            })
            .setNegativeButton("No", DialogInterface.OnClickListener {
                    dialog, id ->dialog.cancel()
            })

        val alert = dialogBuilder.create()

        alert.setTitle("Confirmation")
        alert.show()
    }
}