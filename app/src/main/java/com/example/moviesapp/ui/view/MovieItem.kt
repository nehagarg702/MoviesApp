package com.example.moviesapp.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.moviesapp.data.model.Movie

@Composable
fun MovieItem(
    movie: Movie,
    onMovieClick: (Long) -> Unit,
) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .clickable {
                onMovieClick(movie.id)
            }
            .padding(8.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500/${movie.posterPath}"),
            contentDescription = movie.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(androidx.compose.foundation.shape.RoundedCornerShape(8.dp))
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = movie.title,
            style = com.example.moviesapp.ui.theme.Typography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Left,
                fontWeight = FontWeight(500)
            ),
            modifier = Modifier.wrapContentSize()
        )
    }
}
