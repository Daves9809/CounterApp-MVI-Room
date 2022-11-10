package com.example.mvi_example.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CounterDao {

    //initialize
   @Insert
    suspend fun initialize(counter: Counter)

    //get
    @Query("SELECT * FROM counter_table WHERE id = :id")
    fun getCounter(id: Int): Flow<Counter>

    //increase, update
    @Update()
    suspend fun increaseOrDecrease(counter: Counter)

    //increase, update
    @Query("UPDATE counter_table SET count = count + 1 WHERE id = :id")
    suspend fun increaseCounter(id: Int)

    //increase, update
    @Query("UPDATE counter_table SET count = count - 1 WHERE id = :id")
    suspend fun decreaseCounter(id: Int)

    //reset
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun reset(counter: Counter)

    @Query("SELECT (SELECT COUNT(*) FROM counter_table) == 0")
    suspend fun isEmpty(): Boolean
}