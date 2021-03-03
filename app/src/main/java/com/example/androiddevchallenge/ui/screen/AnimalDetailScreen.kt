/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
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
    pagerState: PagerState = remember { PagerState() },
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
                    Spacer(modifier = Modifier.height(16.dp))
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
