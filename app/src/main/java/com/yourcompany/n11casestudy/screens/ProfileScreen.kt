package com.yourcompany.n11casestudy.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yourcompany.n11casestudy.ui.theme.N11CaseStudyTheme

@Composable
fun ProfileScreen(
    profileName: String?, onNavigateBack: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = profileName.toString(),
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold)
        )
        Button(onClick = { onNavigateBack() }) {
            Text("HOME")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ProfileScreenView() {
    N11CaseStudyTheme {
        ProfileScreen(profileName = "", onNavigateBack = {})
    }
}