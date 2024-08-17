package com.example.moviesapp.ui.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.moviesapp.ui.theme.Typography
import com.example.moviesapp.viewmodel.MovieViewModel

@Composable
fun SearchBar(searchQuery: MutableState<String>, viewModel: MovieViewModel){
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(16.dp,24.dp,16.dp,0.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color(0XFFE3E8EF))
    ) {
        TextField(
            value = searchQuery.value,
            onValueChange = { query ->
                viewModel.filterMovies(query)
            },
            modifier = Modifier.fillMaxWidth().focusable(false),
            textStyle = Typography.bodyLarge,
            placeholder = { Text("Search movies", style = Typography.bodyLarge.copy( color = Color(0XFF9AA4B2))) },
            singleLine = true,
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "search", tint = Color(0XFF9AA4B2) ) },
            trailingIcon = {
                if (searchQuery.value.isNotEmpty()) {
                    IconButton(onClick = { viewModel.filterMovies("") }) {
                        Icon(Icons.Default.Close, contentDescription = "Clear",  tint = Color(0XFF9AA4B2) )
                    }
                }
            },
            colors = TextFieldDefaults.colors(
                cursorColor = Color.Gray,
                focusedContainerColor = MaterialTheme.colorScheme.background,
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                focusedIndicatorColor = MaterialTheme.colorScheme.background,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.background,
            ),
            maxLines = 1
        )
    }
}