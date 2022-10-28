package com.example.mvi_example.ui.home

import androidx.lifecycle.ViewModel
import com.example.mvi_example.ui.home.state.HomeEffects
import com.example.mvi_example.ui.home.state.HomeEvents
import com.example.mvi_example.ui.home.state.HomePartialState
import com.example.mvi_example.ui.home.state.HomeState
import com.example.mvi_example.data.CounterRepository
import com.tomcz.ellipse.Processor
import com.tomcz.ellipse.common.processor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

typealias ScreenProcessor = Processor<HomeEvents, HomeState, HomeEffects>

@HiltViewModel
class HomeViewModel @Inject constructor(
    counterRepository: CounterRepository
) : ViewModel() {

    val processor: ScreenProcessor = processor(HomeState(count = 0)) { event ->
        when (event) {
            is HomeEvents.IncreaseButtonClick -> flow {
                val value = increaseByOne(true, counterRepository)
                emit(HomePartialState.SetValue(value))
            }
            is HomeEvents.DecreaseButtonClick -> flow {
                val value = increaseByOne(false, counterRepository)
                emit(HomePartialState.SetValue(value))
            }
            is HomeEvents.ResetCount -> flow {
                counterRepository.resetValueCount()
                emit(HomePartialState.SetValue(0))
            }
            is HomeEvents.StartedApp -> flow {
                counterRepository.getCounterValue()?.count.let { value ->
                    if (value != null)
                        emit(HomePartialState.SetValue(value))
                    else
                        counterRepository.initializeCounter()
                }
            }
        }
    }

    private suspend fun increaseByOne(
        increase: Boolean,
        counterRepository: CounterRepository
    ): Int {
        val actualValue = counterRepository.getCounterValue()!!.count
        return if (increase) {
            counterRepository.increaseOrDecreaseCounter(actualValue + 1)
            actualValue + 1
        } else {
            counterRepository.increaseOrDecreaseCounter(actualValue - 1)
            actualValue - 1
        }
    }
}