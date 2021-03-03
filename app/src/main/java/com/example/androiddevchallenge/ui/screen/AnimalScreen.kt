package com.example.androiddevchallenge.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.animals
import com.example.androiddevchallenge.entities.Animal
import com.example.androiddevchallenge.ui.components.Chip
import com.example.androiddevchallenge.ui.theme.grey
import com.example.androiddevchallenge.ui.theme.purple200
import com.example.androiddevchallenge.ui.theme.teal200
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun AnimalsScreen(
    animals: List<Animal>,
    showBack: Boolean,
    onBackPressed: () -> Unit,
    onItemClick: (id: Int) -> Unit
) {
    Scaffold(
        topBar = {
            AnimalsTopAppBar(
                topAppBarText = stringResource(id = R.string.animals),
                showBack,
                onBackPressed = onBackPressed
            )
        },
        content = {
            Animals(
                animals,
                modifier = Modifier.fillMaxWidth(),
                onItemClick
            )
        }
    )
}

@Composable
fun Animals(
    animals: List<Animal>,
    modifier: Modifier = Modifier,
    onClick: (id: Int) -> Unit
) {
    LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
        items(animals) {animal ->
            AnimalRow(animal, onClick)
        }
    }
}

@Composable
fun AnimalRow(animal: Animal, onClick: (id: Int) -> Unit) {
    Card(
        backgroundColor = grey,
        modifier = Modifier.padding(8.dp)
    ) {
        Spacer(modifier = Modifier.height(44.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.clickable(onClick = {onClick(animal.id)}).fillMaxWidth()) {
            CoilImage(
                data = animal.photos.first().medium,
                fadeIn = true,
                modifier = Modifier
                    .padding(16.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .align(Alignment.CenterVertically),
                contentDescription = null,
                loading = {
                    Box(Modifier.matchParentSize()) {
                        CircularProgressIndicator(Modifier.align(Alignment.Center))
                    }
                }
            )
            Column(modifier = Modifier.fillMaxWidth().align(Alignment.CenterVertically)) {
                Text(
                    text = animal.name,
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier.padding(16.dp)
                        .align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center
                )
                Row(modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)) {
                    Chip("Type: ${animal.type}")
                    Chip("Age: ${animal.age}")
                }
                Row(modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)) {
                    Chip("Size: ${animal.size}")
                    Chip("Gender: ${animal.gender}")
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewAnimalRow() {
    AnimalRow(animals.first()) {}
}

@Composable
fun AnimalsTopAppBar(topAppBarText: String, showBack: Boolean, onBackPressed: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = topAppBarText,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        },
        navigationIcon = if (showBack) {
            {
                IconButton(onClick = onBackPressed) {
                    Icon(
                        imageVector =
                        Icons.Filled.ChevronLeft,
                        contentDescription = stringResource(id = R.string.back)
                    )
                }
            }
        } else {
            {
                Image(
                    painter = painterResource(id = R.drawable.ic_paw),
                    contentDescription = stringResource(id = R.string.animals),
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                )
            }
        },
        actions = {
            Spacer(modifier = Modifier.width(68.dp))
        },
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp
    )
}
