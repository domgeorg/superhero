package com.georgiopoulos.feature.details

import androidx.annotation.StringRes
import com.georgiopoulos.core.model.error.ErrorModel
import com.georgiopoulos.core.model.superhero.SuperHeroModel
import com.georgiopoulos.core_resources.R as Resources

data class SuperHeroDetailsUiState(
    val isLoading: Boolean = true,
    val displayErrorDialog: Boolean = false,
    val isInSquad: Boolean = false,
    val squadChangeRequested: Boolean = false,
    val displayAreYouSureDialog: Boolean = false,
    val displayHireFireButton: Boolean = false,
    @StringRes val hireFireButtonTextResId: Int = Resources.string.add_hero,
    val superHero: SuperHeroModel = SuperHeroModel(
        id = 0,
        name = "",
        standardImageUrl = "",
        description = "",
    ),
)