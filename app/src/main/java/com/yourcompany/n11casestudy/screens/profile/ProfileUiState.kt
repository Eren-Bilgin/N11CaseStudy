package com.yourcompany.n11casestudy.screens.profile

import com.yourcompany.n11casestudy.data.model.UserDetail

sealed interface ProfileUiState {
    data object Init : ProfileUiState
    data object Loading : ProfileUiState
    data class Success(val user: UserDetail) : ProfileUiState
    data object Error : ProfileUiState
}