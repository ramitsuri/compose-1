package com.example.androiddevchallenge.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.entities.Animal
import com.example.androiddevchallenge.ui.components.Chip
import com.example.androiddevchallenge.ui.components.Pager
import com.example.androiddevchallenge.ui.components.PagerState
import com.example.androiddevchallenge.ui.theme.grey
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun AnimalDetails(
    animal: Animal,
    showBack: Boolean,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
    pagerState: PagerState = remember {PagerState()},
    onMoreClick: () -> Unit
) {
    Scaffold(
        topBar = {
            AnimalsTopAppBar(
                topAppBarText = animal.name,
                showBack,
                onBackPressed = onBackPressed
            )
        },
        content = {
            Card(
                backgroundColor = grey,
                modifier = Modifier.padding(8.dp)
            ) {
                Column {
                    pagerState.maxPage = (animal.photos.size - 1).coerceAtLeast(0)
                    Pager(
                        state = pagerState,
                        modifier = modifier.fillMaxWidth()
                    ) {
                        val photo = animal.photos[page]
                        ImageCarouselItem(
                            imageUrl = photo.full,
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxHeight()
                        )
                    }
                    Text(
                        text = animal.description,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(8.dp)
                    )
                    Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                        Chip("Type: ${animal.type}")
                        Chip("Age: ${animal.age}")
                    }
                    Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                        Chip("Size: ${animal.size}")
                        Chip("Gender: ${animal.gender}")
                    }
                    Spacer (modifier = Modifier.height(16.dp))
                    TextButton(
                        onClick = onMoreClick,
                        modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = "View More Details",
                            color = MaterialTheme.colors.primary
                        )
                    }
                }
            }
        }
    )
}

@Composable
private fun ImageCarouselItem(
    modifier: Modifier = Modifier,
    imageUrl: String? = null,
) {
    Column(
        modifier.padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Box(
            Modifier
                .weight(1f)
                .align(Alignment.CenterHorizontally)
                .aspectRatio(1f)
        ) {
            if (imageUrl != null) {
                CoilImage(
                    data = imageUrl,
                    fadeIn = true,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    loading = {
                        Box(Modifier.matchParentSize()) {
                            CircularProgressIndicator(Modifier.align(Alignment.Center))
                        }
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(MaterialTheme.shapes.medium)
                )
            }
        }
    }
}