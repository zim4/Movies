package com.adzmf.thelistofmovies.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.adzmf.thelistofmovies.core.data.source.remote.network.ApiService
import com.adzmf.thelistofmovies.core.utils.DataMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

@FlowPreview
@ExperimentalCoroutinesApi
class SearchViewModel(apiService: ApiService) : ViewModel() {

    val queryChannel = BroadcastChannel<String>(Channel.CONFLATED)

    val searchResult = queryChannel.asFlow()
        .debounce(300)
        .distinctUntilChanged()
        .filter { it.trim().isNotEmpty() }
        .mapLatest { DataMapper.mapMovieResponsesToDomain(apiService.searchMovies(query = it).results) }
        .asLiveData()
}

