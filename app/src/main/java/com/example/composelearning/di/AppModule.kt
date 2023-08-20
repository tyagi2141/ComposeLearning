package com.example.composelearning.di

import com.example.composelearning.viewModel.DashBoardViewModel
import com.example.composelearning.viewModel.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { DashBoardViewModel(get()) }
    viewModel { DetailViewModel(get(), get()) }
}