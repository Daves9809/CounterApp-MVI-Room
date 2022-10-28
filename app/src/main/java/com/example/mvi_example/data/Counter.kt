package com.example.mvi_example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "counter_table")
data class Counter(
    @PrimaryKey
    val id: Int = 0,
    val count: Int
)
