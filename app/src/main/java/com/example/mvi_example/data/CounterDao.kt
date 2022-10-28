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
    fun getCounter(): Flow<Counter>

    //increase, update
    @Update()
    suspend fun increaseOrDecrease(counter: Counter)

    //increase, update
    @Query("UPDATE counter_table SET count = count + 1 WHERE id = 0 ")
    suspend fun increaseCounter()

    //increase, update
    @Query("UPDATE counter_table SET count = count - 1 WHERE id = 0 ")
    suspend fun decreaseCounter()

    //reset
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun reset(counter: Counter = Counter(0,0))

    @Query("SELECT (SELECT COUNT(*) FROM counter_table) == 0")
    suspend fun isEmpty(): Boolean
}