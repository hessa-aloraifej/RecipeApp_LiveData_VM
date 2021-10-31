package com.example.recipeapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Recipe")
data class Recipe (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id : Int = 0, // this is how can include id if needed
    @ColumnInfo(name = "Title") val title: String,
    @ColumnInfo(name="Author") val author: String,
    @ColumnInfo(name="Ingredients") val ingredients : String,
    @ColumnInfo(name="Instructions") val instructions: String,

        )




@Entity(tableName = "Note")
data class Note (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id : Int = 0, // this is how can include id if needed
    @ColumnInfo(name = "Notes") val note: String

)