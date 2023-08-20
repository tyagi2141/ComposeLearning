package com.example.composelearning.design

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.composelearning.model.Movie
import com.example.composelearning.viewModel.DashBoardState

@Composable
fun DashBoard(
    name: String,
    password: String,
    uiState: DashBoardState,
    loadNextMovie: (Boolean) -> Unit,
    navigateToDetail: (Movie) -> Unit

) {


    Box(modifier = Modifier) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            itemsIndexed(uiState.movieList, key = { _, movie -> movie.id }) { index, movie ->
                MovieItem(movie = movie, onMovieClicked = { navigateToDetail(movie) })

                if (index >= uiState.movieList.size - 1 && !uiState.loading && !uiState.loadFinished) {
                    LaunchedEffect(key1 = Unit, block = { loadNextMovie(false) })
                }
            }

            if (uiState.loading && uiState.movieList.isNotEmpty()) {
                item(span = { GridItemSpan(2) }) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CircularProgressIndicator(color = Color.Red)
                    }
                }
            }
        }
    }

}


@Composable
fun MovieItem(movie: Movie, onMovieClicked: (Movie) -> Unit) {

    Card(
        modifier = Modifier
            .height(200.dp)
            .clickable { onMovieClicked(movie) },
        shape = RoundedCornerShape(8.dp)

    ) {
        Column(
        ) {
            Box(
                modifier = Modifier.weight(1f), contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = movie.imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(bottomStart = 2.dp, bottomEnd = 2.dp))
                )
            }

            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {

                Text(
                    text = movie.title ?: "NA",
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = movie.releaseDate ?: "NA",
                    style = MaterialTheme.typography.subtitle2,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

            }
        }

    }


}

@Preview(showSystemUi = false)
@Composable
fun showItem() {
    MovieItem(Movie(1, "title", "description", "", "date"), {})
}