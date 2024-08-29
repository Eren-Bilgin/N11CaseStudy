@file:OptIn(ExperimentalMaterial3Api::class)

package com.yourcompany.n11casestudy.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(), navigate: (String) -> Unit) {
    val userList by viewModel.uiState.collectAsStateWithLifecycle()


    Column {

        SearchBar(query = viewModel.search,
            onQueryChange = { viewModel.setSearchName(it) },
            onSearch = {},
            placeholder = {
                Text(text = "Search Name")
            },
            active = false,
            onActiveChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            content = {},
            trailingIcon = {

            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        Column {
            when (val result = userList) {
                is HomeUiState.Init -> {}

                is HomeUiState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is HomeUiState.Success -> {
                    val tmp = result.users
                    val newList = tmp.map { it.login }.filter { login ->
                        login?.let {
                            it.length <= 50000
                        } ?: false
                    }
                    HomeContent(
                        loginList = newList
                    )
                }

                is HomeUiState.Error -> {}
            }
        }
    }
}

@Composable
fun HomeContent(
    loginList: List<String?>,
) {
    LazyColumn(
        content = {
            item(loginList) {
                Text(text = AnnotatedString(loginList.joinToString(",\n")))
            }
        }, modifier = Modifier.padding(
            start = 8.dp, top = 4.dp, end = 8.dp, bottom = 4.dp
        )
    )
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(navigate = {})
}