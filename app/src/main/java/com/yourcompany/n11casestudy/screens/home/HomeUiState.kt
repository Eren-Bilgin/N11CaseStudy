package com.yourcompany.n11casestudy.screens.home

import com.yourcompany.n11casestudy.data.model.User

sealed interface HomeUiState {
    data object Init : HomeUiState
    data object Loading : HomeUiState
    data class Success(val users: List<User>) : HomeUiState
    data object Error : HomeUiState
}