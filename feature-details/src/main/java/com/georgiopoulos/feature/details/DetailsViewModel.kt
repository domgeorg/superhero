package com.georgiopoulos.feature.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.georgiopoulos.core.data.repository.details.DetailsRepository
import com.georgiopoulos.core.model.superhero.SuperHeroModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.georgiopoulos.core_resources.R as Resources

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val detailsRepository: DetailsRepository,
) : ViewModel() {

    private val mutableUiState = MutableStateFlow(SuperHeroDetailsUiState())
    val uiState = mutableUiState.asStateFlow()

    private lateinit var superHero: SuperHeroModel

    init {
        triggerEvent(DetailsEvent.RenderSuperHero)
        triggerEvent(DetailsEvent.CheckSquadMembership)
    }


    fun triggerEvent(event: DetailsEvent) {
        when (event) {
            DetailsEvent.RenderSuperHero -> renderSuperHero()
            DetailsEvent.CheckSquadMembership -> checkSquadMembership()
            DetailsEvent.AddInSquad -> addToSquad()
            DetailsEvent.RemoveFromSquad -> removeFromSquad()
            is DetailsEvent.RemoveFromSquadIntention -> removeFromSquadIntention(
                isDismissAction = event.isDismissAction
            )

            DetailsEvent.DismissError -> dismissError()
        }
    }

    private fun renderSuperHero() {
        superHero = detailsRepository.getSuperHero() ?: throw IllegalStateException("SuperHero is null")
        mutableUiState.update { previousUiState ->
            previousUiState.copy(superHero = superHero)
        }

        detailsRepository.clearSuperHeroData()
    }

    private fun checkSquadMembership() {
        viewModelScope.launch {
            try {
                detailsRepository.isInSquad(superHero.name).collect { isInSquad ->
                    mutableUiState.update { previousUiState ->
                        previousUiState.copy(
                            isLoading = false,
                            displayHireFireButton = true,
                            isInSquad = isInSquad,
                            hireFireButtonTextResId = if (isInSquad) {
                                Resources.string.remove_hero
                            } else {
                                Resources.string.add_hero
                            },
                        )
                    }
                }
            } catch (exception: Exception) {
                mutableUiState.update { previousUiState ->
                    previousUiState.copy(
                        isLoading = false,
                        displayHireFireButton = false,
                    )
                }
            }
        }
    }

    private fun addToSquad() {
        viewModelScope.launch {
            try {
                detailsRepository.addInSquad(superHero)
                mutableUiState.update { previousUiState ->
                    previousUiState.copy(
                        isInSquad = true,
                        hireFireButtonTextResId = Resources.string.remove_hero,
                        squadChangeRequested = !previousUiState.squadChangeRequested,
                    )
                }
            } catch (exception: Exception) {
                mutableUiState.update { previousUiState ->
                    previousUiState.copy(
                        displayErrorDialog = true
                    )
                }
            }
        }
    }

    private fun removeFromSquad() {
        viewModelScope.launch {
            try {
                detailsRepository.removeFromSquad(superHero)
                mutableUiState.update { previousUiState ->
                    previousUiState.copy(
                        isInSquad = false,
                        displayAreYouSureDialog = false,
                        hireFireButtonTextResId = Resources.string.add_hero,
                        squadChangeRequested = !previousUiState.squadChangeRequested,
                    )
                }
            } catch (exception: Exception) {
                mutableUiState.update { previousUiState ->
                    previousUiState.copy(
                        displayErrorDialog = true,
                    )
                }
            }
        }
    }

    private fun removeFromSquadIntention(isDismissAction: Boolean) {
        mutableUiState.update { previousUiState ->
            previousUiState.copy(displayAreYouSureDialog = !isDismissAction)
        }
    }

    private fun dismissError() {
        mutableUiState.update { previousUiState ->
            previousUiState.copy(
                displayErrorDialog = false,
            )
        }
    }
}
