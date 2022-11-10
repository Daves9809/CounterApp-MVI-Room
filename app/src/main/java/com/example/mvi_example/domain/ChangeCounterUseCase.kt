package com.example.mvi_example.domain

import com.example.mvi_example.data.repository.CounterRepository
import javax.inject.Inject

// UseCase shouldn't be a Singleton
class ChangeCounterUseCase @Inject constructor(
    private val counterRepository: CounterRepository
) : suspend (ChangeCounterUseCase.Param) -> Unit {

    override suspend fun invoke(param: Param) {
        when (param) {
            Param.INCREASE -> counterRepository.increaseCounter()
            Param.DECREASE -> counterRepository.decreaseCounter()
        }
    }

    enum class Param {
        INCREASE, DECREASE
    }
}
