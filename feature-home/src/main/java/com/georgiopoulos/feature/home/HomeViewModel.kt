package com.georgiopoulos.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.georgiopoulos.core.data.repository.home.HomeRepository
import com.georgiopoulos.core.model.superhero.SuperHeroModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
) : ViewModel() {

    val superHeroNetworkPagingData: Flow<PagingData<SuperHeroModel>> by lazy {
        homeRepository.fetchSuperHeroListFromNetwork().cachedIn(viewModelScope)
    }

    val superHeroStoragePagingData: Flow<PagingData<SuperHeroModel>> by lazy {
        homeRepository.fetchSuperHeroListFromStorage().cachedIn(viewModelScope)
    }

    fun passSuperHeroData(superHero: SuperHeroModel) {
        homeRepository.save(superHero)
    }
}
