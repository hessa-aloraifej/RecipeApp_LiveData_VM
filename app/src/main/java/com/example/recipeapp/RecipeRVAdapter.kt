package com.example.recipeapp

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cardview.view.*
import kotlinx.android.synthetic.main.item_row.view.*

class RecipeRVAdapter(private val activity:ViewRecipe,private val list: List<Recipe>) : RecyclerView.Adapter<RecipeRVAdapter.ItemViewHolder>() {
    class ItemViewHolder (itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.cardview,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val recipe = list[position]

        holder.itemView.apply {
            tvTitle.text = " Title: ${recipe.title}"
            tvAuthor.text =" Author: ${recipe.author}"

           // textView3.text =" Author: ${recipe.ingredients}"
            //            textView4.text =" Author: ${recipe.instructions}"
              cvMain.setOnClickListener {
                           Toast.makeText(holder.itemView.context," The Ingredients :${recipe.ingredients}\n The Instructions :${recipe.instructions}", Toast.LENGTH_SHORT).show()
                        }
            delbtn.setOnClickListener(){
               activity.confirmAlert(recipe)

            }
            editbtn.setOnClickListener {
                activity.editAlert(recipe.id,recipe.title,recipe.author,recipe.ingredients,recipe.instructions)

            }
        }
    }

    override fun getItemCount(): Int = list.size
}