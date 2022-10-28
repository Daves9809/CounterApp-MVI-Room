package com.example.mvi_example.ui.home.state

sealed interface HomeEvents{
    object IncreaseButtonClick: HomeEvents
    object DecreaseButtonClick: HomeEvents
    object StartedApp: HomeEvents
    object ResetCount: HomeEvents
}