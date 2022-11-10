package com.example.mvi_example.data.repository

import android.util.Log
import com.example.mvi_example.data.Counter
import com.example.mvi_example.data.CounterDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CounterRepository @Inject constructor(
    private val counterDao: CounterDao
) {

    fun getCounterValue(): Flow<Counter> = counterDao.getCounter(COUNTER_ID)

    suspend fun resetValueCount() = counterDao.reset(Counter(COUNTER_ID, 0))

    suspend fun isEmpty(): Boolean = counterDao.isEmpty()

    suspend fun increaseCounter() = counterDao.increaseCounter(COUNTER_ID)

    suspend fun decreaseCounter() = counterDao.decreaseCounter(COUNTER_ID)

    companion object {
        private const val COUNTER_ID = 0
    }
}