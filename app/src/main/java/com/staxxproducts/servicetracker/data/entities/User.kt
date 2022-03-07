package com.staxxproducts.servicetracker.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @ColumnInfo(name = "userId", index = true)
    @PrimaryKey(autoGenerate = true)
    val userId: Long?,
    val userName: String,
    val userPassword: String,
    val userType: String

)
