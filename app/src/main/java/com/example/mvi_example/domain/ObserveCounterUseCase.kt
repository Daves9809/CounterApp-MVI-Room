package com.example.mvi_example.domain

import com.example.mvi_example.data.Counter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class ObserveCounterUseCase(override val dispatcher: CoroutineDispatcher = Dispatchers.IO) :
    ObservableUseCase<Counter> {
    override fun observe(): Flow<Counter> {
        TODO("Not yet implemented")
    }
}