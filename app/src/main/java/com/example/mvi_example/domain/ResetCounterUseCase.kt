package com.example.mvi_example.domain

import com.example.mvi_example.data.repository.CounterRepository

class ResetCounterUseCase(private val counterRepository: CounterRepository) {
    suspend fun invoke(){
        counterRepository.resetValueCount()
    }
}