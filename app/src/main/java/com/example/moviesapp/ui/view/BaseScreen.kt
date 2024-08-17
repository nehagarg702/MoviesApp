package com.example.moviesapp.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.moviesapp.network.NetworkResult
import com.example.moviesapp.utils.UiState

//Base Screen for handling Loading and Error state for all network calls screen
@Composable
fun <T> BaseScreen(
    uiState: NetworkResult<T>,
    onRetry: () -> Unit,
    content: @Composable (T) -> Unit
) {
    when (uiState) {
        is NetworkResult.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color(0xFFEC4172))
            }
        }
        is NetworkResult.Error -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp), // Add padding if necessary
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth() // Ensure content is centered horizontally
                ) {
                    Text(
                        text = uiState.message,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = onRetry,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFEC4172)
                        ),
                        modifier = Modifier.padding(horizontal = 32.dp)
                    ) {
                        Text(
                            text = "Retry",
                            color = Color.White, // Text color
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
        is NetworkResult.Success -> {
            content(uiState.data)
        }
    }
}

