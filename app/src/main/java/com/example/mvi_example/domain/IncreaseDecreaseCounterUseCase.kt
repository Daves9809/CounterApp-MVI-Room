package com.example.mvi_example.domain

import com.example.mvi_example.data.repository.CounterRepository
import javax.inject.Inject

class IncreaseDecreaseCounterUseCase @Inject constructor(private val counterRepository: CounterRepository) {
    suspend fun invoke(increase: Boolean){
        if (increase)
            counterRepository.increaseCounter()
        else
            counterRepository.decreaseCounter()
    }
}