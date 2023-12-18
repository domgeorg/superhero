package com.georgiopoulos.feature.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.georgiopoulos.core.data.repository.details.DetailsRepository
import com.georgiopoulos.core.model.superhero.SuperHeroModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.georgiopoulos.core_resources.R as Resources

@OptIn(ExperimentalCoroutinesApi::class)
class DetailsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val detailsRepository: DetailsRepository = mockk(relaxed = true)

    private lateinit var detailsViewModel: DetailsViewModel

    private val superhero = SuperHeroModel(
        id = 1,
        name = "Ant-Man",
        standardImageUrl = "url",
        description = "Description"
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        detailsViewModel = DetailsViewModel(detailsRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `When viewModel is created, Then UI State should indicate loading and default values`() =
        runTest {
            // Given
            // When

            // Then
            val initialState = detailsViewModel.uiState.value

            assertTrue(initialState.isLoading)
        }

    @Test
    fun `Given repository returns superhero, When RenderSuperHero is triggered, Then superhero details should be set in UI state and clear superhero data`() =
        runTest {
            // Given
            every { detailsRepository.getSuperHero() } returns superhero

            // When
            detailsViewModel.triggerEvent(DetailsEvent.RenderSuperHero)

            // Then
            val updatedState = detailsViewModel.uiState.value

            assertEquals(
                /* expected = */ SuperHeroDetailsUiState(
                    isLoading = true,
                    displayErrorDialog = false,
                    isInSquad = false,
                    squadChangeRequested = false,
                    displayAreYouSureDialog = false,
                    displayHireFireButton = false,
                    hireFireButtonTextResId = Resources.string.add_hero,
                    superHero = superhero
                ), /* actual = */ updatedState
            )

            verify { detailsRepository.clearSuperHeroData() }
        }

    @Test
    fun `Given superhero is in squad, When CheckSquadMembership is triggered, Then UI state should reflect superhero in squad`() =
        runTest {
            // Given
            coEvery { detailsRepository.isInSquad(any()) } returns MutableStateFlow(true)

            // When
            detailsViewModel.triggerEvent(DetailsEvent.CheckSquadMembership)

            // Then
            val updatedState = detailsViewModel.uiState.value
            assertTrue(
                !updatedState.isLoading &&
                        updatedState.displayHireFireButton &&
                        updatedState.isInSquad &&
                        updatedState.hireFireButtonTextResId == Resources.string.remove_hero
            )
        }

    @Test
    fun `Given superhero is not in squad, When checkSquadMembership is called, Then UI state should reflect superhero not in squad`() =
        runTest {
            // Given
            coEvery { detailsRepository.isInSquad(any()) } returns MutableStateFlow(false)

            // When
            detailsViewModel.triggerEvent(DetailsEvent.CheckSquadMembership)

            // Then
            val updatedState = detailsViewModel.uiState.value
            assertTrue(
                !updatedState.isLoading &&
                        updatedState.displayHireFireButton &&
                        !updatedState.isInSquad &&
                        updatedState.hireFireButtonTextResId == Resources.string.add_hero
            )
        }

    @Test
    fun `Given error, When checkSquadMembership is called, Then UI state should reflect the inability to retrieve the membership`() =
        runTest {
            // Given
            coEvery { detailsRepository.isInSquad(any()) } returns MutableStateFlow(false)

            // When
            detailsViewModel.triggerEvent(DetailsEvent.CheckSquadMembership)

            // Then
            val updatedState = detailsViewModel.uiState.value
            assertFalse(updatedState.isLoading && updatedState.displayHireFireButton)
        }

    @Test
    fun `Given superhero is added to squad successfully, When addToSquad is called, Then UI state should reflect superhero in squad`() =
        runTest {
            // Given
            coEvery { detailsRepository.addInSquad(superhero) } returns Unit

            // When
            detailsViewModel.triggerEvent(DetailsEvent.AddInSquad)

            // Then
            val updatedState = detailsViewModel.uiState.value
            assertTrue(
                updatedState.isInSquad &&
                        updatedState.squadChangeRequested &&
                        updatedState.hireFireButtonTextResId == Resources.string.remove_hero
            )
        }

    @Test
    fun `Given error while adding superhero to squad, When addToSquad is called, Then UI state should reflect displayErrorDialog`() =
        runTest {
            // Given
            coEvery { detailsRepository.addInSquad(any()) } coAnswers {
                throw Exception("Add to squad failed")
            }

            // When
            detailsViewModel.triggerEvent(DetailsEvent.AddInSquad)

            // Then
            val updatedState = detailsViewModel.uiState.value
            assertTrue(updatedState.displayErrorDialog)
        }

    @Test
    fun `Given superhero is removed from squad successfully, When removeFromSquad is called, Then UI state should reflect superhero not in squad`() =
        runTest {
            // Given
            coEvery { detailsRepository.removeFromSquad(any()) } returns Unit

            // When
            detailsViewModel.triggerEvent(DetailsEvent.RemoveFromSquad)

            // Then
            val updatedState = detailsViewModel.uiState.value
            assertTrue(
                !updatedState.isInSquad &&
                        !updatedState.displayAreYouSureDialog &&
                        updatedState.squadChangeRequested &&
                        updatedState.hireFireButtonTextResId == Resources.string.add_hero
            )
        }


    @Test
    fun `Given error while removing superhero from squad, When removeFromSquad is called, Then UI state should reflect displayErrorDialog`() =
        runTest {
            // Given
            coEvery { detailsRepository.removeFromSquad(any()) } coAnswers {
                throw Exception("Remove from squad failed")
            }

            // When
            detailsViewModel.triggerEvent(DetailsEvent.RemoveFromSquad)

            // Then
            val updatedState = detailsViewModel.uiState.value
            assertTrue(updatedState.displayErrorDialog)
        }


    @Test
    fun `Given DetailsEvent RemoveFromSquadIntention with isDismissAction true, When removeFromSquadIntention is called, Then UI state should reflect displayAreYouSureDialog as false`() =
        runTest {
            // Given

            // When
            detailsViewModel.triggerEvent(DetailsEvent.RemoveFromSquadIntention(isDismissAction = true))

            // Then
            val updatedState = detailsViewModel.uiState.value
            assertFalse(updatedState.displayAreYouSureDialog)
        }

    @Test
    fun `Given DetailsEvent RemoveFromSquadIntention with isDismissAction false, When removeFromSquadIntention is called, Then UI state should reflect displayAreYouSureDialog as true`() =
        runTest {
            // Given

            // When
            detailsViewModel.triggerEvent(DetailsEvent.RemoveFromSquadIntention(isDismissAction = false))

            // Then
            val updatedState = detailsViewModel.uiState.value
            assertTrue(updatedState.displayAreYouSureDialog)
        }

    @Test
    fun `Given DetailsEvent DismissError, When triggerEvent is called, Then UI state should reflect displayErrorDialog as false`() =
        runTest {
            // Given

            // When
            detailsViewModel.triggerEvent(DetailsEvent.DismissError)

            // Then
            val updatedState = detailsViewModel.uiState.value
            assertFalse(updatedState.displayErrorDialog)
        }
}
