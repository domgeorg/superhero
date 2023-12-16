package com.georgiopoulos.feature.details

sealed interface DetailsEvent {
    data object RemoveFromSquad : DetailsEvent
    data object AddInSquad : DetailsEvent
    data class RemoveFromSquadIntention(
        val isDismissAction: Boolean = false,
    ) : DetailsEvent

    data object DismissError : DetailsEvent
}