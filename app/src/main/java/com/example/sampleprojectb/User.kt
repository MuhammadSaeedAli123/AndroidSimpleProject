package com.example.sampleprojectb

import android.os.Parcelable
import android.text.Editable
import androidx.room.Entity
import androidx.room.PrimaryKey


import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "user_table")
data class User(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val firstName: String,
    val lastName: String,
    val age: Int

): Parcelable
