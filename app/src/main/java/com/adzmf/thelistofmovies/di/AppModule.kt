package com.adzmf.thelistofmovies.di

import com.adzmf.thelistofmovies.core.domain.usecase.MovieInteractor
import com.adzmf.thelistofmovies.core.domain.usecase.MovieUseCase
import com.adzmf.thelistofmovies.detail.DetailViewModel
import com.adzmf.thelistofmovies.home.HomeViewModel
import com.adzmf.thelistofmovies.search.SearchViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

@ExperimentalCoroutinesApi
@FlowPreview
val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { SearchViewModel(get()) }
}