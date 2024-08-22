package com.yourcompany.n11casestudy.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yourcompany.n11casestudy.R
import com.yourcompany.n11casestudy.ui.theme.N11CaseStudyTheme

@Composable
fun HomeScreen(navigate: (String) -> Unit) {
    var searchName by remember { mutableStateOf("") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.welcome),
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold)
        )
        OutlinedTextField(value = searchName,
            onValueChange = { searchName = it },
            label = { Text("Search") })
        Button(onClick = { navigate(searchName) }) {
            Text("Profile")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenView() {
    N11CaseStudyTheme {
        HomeScreen(navigate = {})
    }
}