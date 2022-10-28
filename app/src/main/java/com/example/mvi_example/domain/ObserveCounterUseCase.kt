package com.example.mvi_example.domain

import com.example.mvi_example.data.Counter
import com.example.mvi_example.data.repository.CounterRepository
import kotlinx.coroutines.flow.Flow

class ObserveCounterUseCase(
    private val counterRepository: CounterRepository,
) : FlowUseCase<Counter>() {
    override suspend fun performAction(): Flow<Counter> {
        if (counterRepository.isEmpty())
            counterRepository.initializeCounter()

        return counterRepository.getCounterValue()
    }
}
