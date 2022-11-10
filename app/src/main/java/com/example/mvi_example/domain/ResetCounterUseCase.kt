package com.example.mvi_example.domain

import com.example.mvi_example.data.repository.CounterRepository
import javax.inject.Inject

// UseCase shouldn't be a Singleton
class ResetCounterUseCase @Inject constructor(
    private val counterRepository: CounterRepository
) : suspend () -> Unit {

    override suspend fun invoke() {
        counterRepository.resetValueCount()
    }
}