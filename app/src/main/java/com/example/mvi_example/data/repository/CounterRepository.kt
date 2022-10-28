package com.example.mvi_example.data.repository

import android.util.Log
import com.example.mvi_example.data.Counter
import com.example.mvi_example.data.CounterDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class CounterRepository(
    private val counterDao: CounterDao
) {

    suspend fun initializeCounter() {
        Log.d("TAG","Initialized")
        counterDao.initialize()
    }

    fun getCounterValue(): Flow<Counter> = counterDao.getCounter().flowOn(Dispatchers.IO)

    suspend fun resetValueCount() = counterDao.reset()

    suspend fun isEmpty():Boolean = counterDao.isEmpty()

    suspend fun increaseCounter() = counterDao.increaseCounter()

    suspend fun decreaseCounter() = counterDao.decreaseCounter()
}