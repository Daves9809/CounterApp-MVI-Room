package com.example.mvi_example.data

import android.util.Log
import kotlinx.coroutines.flow.Flow

class CounterRepository(
    private val counterDao: CounterDao
) {

    suspend fun initializeCounter() {
        Log.d("TAG","Initialized")
        counterDao.initialize()
    }

    suspend fun getCounterValue(): Counter? = counterDao.getCounter()

    suspend fun increaseOrDecreaseCounter(value: Int) =
        counterDao.increaseOrDecrease(Counter(id = 0, count = value))

    suspend fun resetValueCount() = counterDao.reset()

    suspend fun isEmpty():Boolean = counterDao.isEmpty()
}