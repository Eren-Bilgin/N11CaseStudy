package com.yourcompany.n11casestudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.yourcompany.n11casestudy.navigation.NavigationHost
import com.yourcompany.n11casestudy.ui.theme.N11CaseStudyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            N11CaseStudyTheme {
                NavigationHost()
            }
        }
    }
}

