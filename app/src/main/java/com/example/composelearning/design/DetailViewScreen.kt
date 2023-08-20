package com.example.composelearning.design

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.composelearning.viewModel.DetailScreenState

@Composable
fun DetailViewScreen(uiState: DetailScreenState) {

    Column {
        Box(modifier = Modifier.height(200.dp)) {
            AsyncImage(
                model = uiState.movie?.imageUrl,
                contentDescription = "", modifier = Modifier
                    .fillMaxSize()
                    .height(320.dp), contentScale = ContentScale.FillBounds


            )
        }

        Text(text = uiState.movie?.description ?: "NA")
    }
}