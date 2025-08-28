@file:OptIn(ExperimentalMaterial3Api::class)

package com.yourcompany.n11casestudy.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.yourcompany.n11casestudy.R
import com.yourcompany.n11casestudy.data.model.User

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigate: (String) -> Unit
) {
    val userList = viewModel.uiState.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
    ) {
        SearchBar(
            query = viewModel.search,
            onQueryChange = { viewModel.setSearchName(it) },
            onSearch = {},
            placeholder = {
                Text(text = "Search Name")
            },
            active = false,
            onActiveChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 16.dp,
                    start = 16.dp,
                    end = 16.dp
                ),
            content = {},
            trailingIcon = {
                if (viewModel.search.isNotEmpty()) {
                    IconButton(onClick = { viewModel.search = "" }) {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = "Clear")
                    }
                }
            })

        if (viewModel.search.isNotEmpty() && userList.loadState.refresh is LoadState.Loading) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }
        if (userList.loadState.refresh is LoadState.NotLoading) {
            HomeContent(loginList = userList, navigate = navigate)
        }
        if (userList.loadState.refresh is LoadState.Error) {
            val e = userList.loadState.refresh as LoadState.Error
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = "Error",
                        tint = MaterialTheme.colorScheme.error,
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = e.error.message ?: stringResource(R.string.error_message),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}


@Composable
fun HomeContent(
    loginList: LazyPagingItems<User>, navigate: (String) -> Unit
) {
    LazyColumn(
        content = {
            items(loginList.itemCount, key = loginList.itemKey { it.id }) {
                val user = loginList[it]
                ListItem(
                    modifier = Modifier.clickable { navigate(user?.login.orEmpty()) },
                    headlineContent = {
                        Text(text = user?.login.orEmpty())
                    },
                    leadingContent = {
                        user?.avatarUrl?.let {
                            AsyncImage(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape),
                                model = ImageRequest.Builder(LocalContext.current).data(it)
                                    .crossfade(true).build(),
                                placeholder = rememberVectorPainter(image = Icons.Default.Person),
                                contentDescription = ""
                            )
                        }
                    })
            }
        }, modifier = Modifier.padding(
            start = 8.dp, top = 4.dp, end = 8.dp, bottom = 4.dp
        )
    )
}


@Preview(showBackground = true)
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun PreviewHomeScreen() {
    var query by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(
            top = 16.dp,
            start = 16.dp,
            end = 16.dp
        )
    ) {
        SearchBar(
            query = query,
            onQueryChange = { query = it },
            onSearch = {},
            placeholder = { Text("Search Name") },
            active = false,
            onActiveChange = {},
            modifier = Modifier
                .fillMaxWidth(),
            trailingIcon = {
                if (query.isNotEmpty()) {
                    IconButton(onClick = { query = "" }) {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = "Clear")
                    }
                }
            },
            content = {}
        )
    }
}
