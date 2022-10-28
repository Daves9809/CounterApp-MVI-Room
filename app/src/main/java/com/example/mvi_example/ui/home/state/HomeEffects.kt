package com.example.mvi_example.ui.home.state

sealed interface HomeEffects{
    object ShowLoading: HomeEffects
    object HideLoading: HomeEffects
}