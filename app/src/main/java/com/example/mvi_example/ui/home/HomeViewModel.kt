package com.example.mvi_example.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvi_example.ui.home.state.HomeEffects
import com.example.mvi_example.ui.home.state.HomeEvents
import com.example.mvi_example.ui.home.state.HomePartialState
import com.example.mvi_example.ui.home.state.HomeState
import com.example.mvi_example.domain.IncreaseDecreaseCounterUseCase
import com.example.mvi_example.domain.ObserveCounterUseCase
import com.example.mvi_example.domain.ResetCounterUseCase
import com.tomcz.ellipse.Processor
import com.tomcz.ellipse.common.processor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

typealias ScreenProcessor = Processor<HomeEvents, HomeState, HomeEffects>

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val observeCounterUseCase: ObserveCounterUseCase,
    private val increaseDecreaseCounterUseCase: IncreaseDecreaseCounterUseCase,
    private val resetCounterUseCase: ResetCounterUseCase
) : ViewModel() {

    init {
        requestData()
    }

    val processor: ScreenProcessor = processor(HomeState(count = 0)) { event ->
        when (event) {
            is HomeEvents.IncreaseButtonClick -> flow {
                increaseDecreaseCounterUseCase.invoke(increase = true)
            }
            is HomeEvents.DecreaseButtonClick -> flow {
                increaseDecreaseCounterUseCase.invoke(increase = false)
            }
            is HomeEvents.ResetCount -> flow {
                resetCounterUseCase.invoke()
            }
            is HomeEvents.StartedApp -> flow {
                observeCounterUseCase.resultFlow.collect { value ->
                    emit(HomePartialState.SetValue(value.count))
                }

            }
        }
    }


    private fun requestData() {
        viewModelScope.launch {
            observeCounterUseCase.launch()
        }
    }
}