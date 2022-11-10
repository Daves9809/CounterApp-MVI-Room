package com.example.mvi_example.domain

import com.example.mvi_example.data.repository.CounterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

// UseCase shouldn't be a Singleton
class ObserveCounterUseCase @Inject constructor(
    private val counterRepository: CounterRepository,
) : () -> Flow<Int> {

    override fun invoke(): Flow<Int> {
        return counterRepository.getCounterValue().map { it.count }
    }
}
