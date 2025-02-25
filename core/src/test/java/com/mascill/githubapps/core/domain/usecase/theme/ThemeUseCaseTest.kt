package com.mascill.githubapps.core.domain.usecase.theme

import com.mascill.githubapps.core.domain.repository.IThemeRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ThemeUseCaseTest {

    private lateinit var themeUseCase: ThemeUseCase

    @Mock
    private lateinit var themeRepository: IThemeRepository

    @Before
    fun setUp() {
        themeUseCase = ThemeInteractor(themeRepository)
        val dummyTheme = false
        `when`(themeRepository.getTheme()).thenReturn(flow { emit(dummyTheme) })
    }

    @Test
    fun `should get data from repository`() = runBlocking {
        val theme = themeUseCase.getTheme().first()

        verify(themeRepository).getTheme()
        verifyNoMoreInteractions(themeRepository)

        println("Expected theme value to be $THEME, but got $theme")
        assertEquals("Expected theme value to be $THEME, but got $theme", THEME, theme)
    }

    @Test
    fun `should save data to repository`() = runBlocking {
        val isDarkModeActive = true
        themeUseCase.saveTheme(isDarkModeActive)

        verify(themeRepository).saveTheme(isDarkModeActive)
        verifyNoMoreInteractions(themeRepository)
    }

    companion object {
        const val THEME = false
    }
}
