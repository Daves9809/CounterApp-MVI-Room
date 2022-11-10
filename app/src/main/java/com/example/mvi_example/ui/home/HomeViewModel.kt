package com.example.mvi_example.ui.home

import androidx.lifecycle.ViewModel
import com.example.mvi_example.domain.ChangeCounterUseCase
import com.example.mvi_example.domain.ObserveCounterUseCase
import com.example.mvi_example.domain.ResetCounterUseCase
import com.example.mvi_example.ui.home.state.HomeEffects
import com.example.mvi_example.ui.home.state.HomeEvents
import com.example.mvi_example.ui.home.state.HomePartialState
import com.example.mvi_example.ui.home.state.HomeState
import com.tomcz.ellipse.Processor
import com.tomcz.ellipse.common.processor
import com.tomcz.ellipse.common.toNoAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

typealias ScreenProcessor = Processor<HomeEvents, HomeState, HomeEffects>

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val observeCounterUseCase: ObserveCounterUseCase,
    private val changeCounterUseCase: ChangeCounterUseCase,
    private val resetCounterUseCase: ResetCounterUseCase
) : ViewModel() {


    val processor: ScreenProcessor = processor(
        initialState = HomeState(),
        prepare = { observeCounterUseCase().map { HomePartialState.SetValue(value = it) } },
        onEvent = { event ->
            when (event) {
                is HomeEvents.IncreaseButtonClick ->
                    changeCounterUseCase(ChangeCounterUseCase.Param.INCREASE).toNoAction()
                is HomeEvents.DecreaseButtonClick ->
                    changeCounterUseCase(ChangeCounterUseCase.Param.DECREASE).toNoAction()
                is HomeEvents.ResetCount ->
                    resetCounterUseCase().toNoAction()
            }
        })

}