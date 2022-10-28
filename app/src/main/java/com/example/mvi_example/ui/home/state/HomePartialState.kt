package com.example.mvi_example.ui.home.state

import com.tomcz.ellipse.PartialState

sealed interface HomePartialState: PartialState<HomeState>{
    class SetValue(val value: Int) : HomePartialState {
        override fun reduce(oldState: HomeState): HomeState {
            return oldState.copy(count = value)
        }
    }

}