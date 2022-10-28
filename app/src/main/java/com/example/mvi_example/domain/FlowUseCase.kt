package com.example.mvi_example.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

abstract class FlowUseCase<T> {

    private val _trigger = MutableStateFlow(true)

    //exposes result of this useCase
    val resultFlow: Flow<T> = _trigger.flatMapLatest {
        performAction()
    }

    //triggers the execution of this use case
    suspend fun launch(){
        _trigger.emit(!(_trigger.value))
    }

    protected abstract suspend fun performAction(): Flow<T>
}