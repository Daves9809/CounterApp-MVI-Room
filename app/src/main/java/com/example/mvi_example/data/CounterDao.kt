package com.example.mvi_example.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CounterDao {

    //initialize
    @Insert
    suspend fun initialize(counter: Counter = Counter(0,0))

    //get
    @Query("SELECT * FROM counter_table WHERE id = 0")
    suspend fun getCounter(): Counter?

    //increase, update
    @Update
    suspend fun increaseOrDecrease(counter: Counter)

    //reset
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun reset(counter: Counter = Counter(0,0))

    @Query("SELECT (SELECT COUNT(*) FROM counter_table) == 0")
    suspend fun isEmpty(): Boolean
}