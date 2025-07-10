package com.example.game.ui.gameDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GameDetailsScreen(
    modifier: Modifier = Modifier, id: Int, onBackCLick: () -> Unit,
) {
    val viewModel = koinViewModel<GameDetailsViewModel>()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(id) {
        viewModel.getGameDetails(id)
    }

    GameDetailScreenContent(
        modifier = modifier,
        uiState.value,
        onSaveCLick = { id, name, image -> },
        onDeleteCLick = {},
        onBackCLick
    )
}

@Composable
fun GameDetailScreenContent(
    modifier: Modifier = Modifier, uiState: GameDetailsScreen.UiState,
    onSaveCLick: (Int, String, String) -> Unit,
    onDeleteCLick: (Int) -> Unit,
    onBackCLick: () -> Unit,
) {
    Scaffold(modifier = modifier.fillMaxSize(),) { innerPadding ->

        if (uiState.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        if (uiState.error.isNotBlank()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(uiState.error)
            }
        }

        uiState.data?.let { data ->
            Box(modifier = modifier.fillMaxSize()) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    item {
                        AsyncImage(
                            model = data.backgroundImage, contentDescription = null,
                            modifier = Modifier.fillMaxWidth().height(350.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                    item {
                        Text(
                            modifier = Modifier.padding(12.dp).fillMaxWidth(),
                            text = data.name,
                            style = MaterialTheme.typography.headlineMedium
                        )
                    }
                    item {
                        Text(
                            text = data.description,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                                .fillMaxWidth(),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    item {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "Platforms",
                                modifier = Modifier.padding(horizontal = 12.dp).padding(top = 24.dp)
                                    .fillMaxWidth(),
                                style = MaterialTheme.typography.headlineMedium
                            )
                            LazyRow(modifier = Modifier.fillMaxWidth()) {
                                items(data.platforms) {
                                    Card(
                                        modifier = Modifier.padding(12.dp).wrapContentSize(),
                                        shape = RoundedCornerShape(12.dp),
                                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                                    ) {
                                        Column(
                                            modifier = Modifier.width(150.dp),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            AsyncImage(
                                                model = it.image, contentDescription = null,
                                                modifier = Modifier.padding(top = 8.dp)
                                                    .background(
                                                        color = Color.Transparent,
                                                        shape = CircleShape
                                                    )
                                                    .clip(CircleShape)
                                                    .size(110.dp),
                                                contentScale = ContentScale.Crop
                                            )
                                            Text(
                                                text = it.name,
                                                style = MaterialTheme.typography.labelLarge,
                                                modifier = Modifier.padding(vertical = 8.dp)
                                            )
                                        }
                                    }
                                }
                            }

                        }
                    }
                    item {
                        Text(
                            text = "Stores",
                            modifier = Modifier.padding(horizontal = 12.dp).padding(top = 24.dp)
                                .fillMaxWidth(),
                            style = MaterialTheme.typography.headlineMedium
                        )
                    }
                    items(data.stores) {
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp).padding(bottom = 8.dp)
                                .fillMaxWidth()
                        ) {
                            AsyncImage(
                                model = it.image, contentDescription = null,
                                modifier = Modifier.size(120.dp).background(
                                    color = Color.Transparent,
                                    shape = RoundedCornerShape(12.dp)
                                ).clip(RoundedCornerShape(12.dp)),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(Modifier.width(8.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = it.name,
                                    style = MaterialTheme.typography.titleMedium,
                                    modifier = Modifier.padding(end = 8.dp)
                                )
                                Spacer(Modifier.height(8.dp))
                                Text(
                                    text = it.domain,
                                    style = MaterialTheme.typography.bodySmall,
                                    textDecoration = TextDecoration.Underline,
                                )
                                Spacer(Modifier.height(8.dp))
                                Text(
                                    text = "Game Count : ${it.gameCount}",
                                    style = MaterialTheme.typography.bodySmall,
                                )
                            }
                        }
                    }
                    item {
                        Text(
                            text = "Tags",
                            modifier = Modifier.padding(horizontal = 12.dp).padding(top = 24.dp)
                                .fillMaxWidth(),
                            style = MaterialTheme.typography.headlineMedium
                        )
                    }
                    item {
                        FlowRow(modifier = Modifier.padding(horizontal = 12.dp).fillMaxWidth()) {
                            data.tags.forEach {
                                Row(
                                    modifier = Modifier.padding(top = 5.dp, end = 5.dp).background(
                                        color = Color.White,
                                        shape = RoundedCornerShape(200.dp)
                                    ).clip(RoundedCornerShape(200.dp))
                                        .border(
                                            width = 1.dp,
                                            color = Color.LightGray,
                                            shape = RoundedCornerShape(200.dp)
                                        ),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    AsyncImage(
                                        model = it.image, contentDescription = null,
                                        modifier = Modifier.size(30.dp).background(
                                            color = Color.Transparent,
                                            shape = CircleShape
                                        ).clip(CircleShape),
                                        contentScale = ContentScale.Crop
                                    )
                                    Spacer(Modifier.width(4.dp))
                                    Text(
                                        text = it.name,
                                        style = MaterialTheme.typography.labelSmall,
                                        modifier = Modifier.padding(end = 8.dp)
                                    )
                                }
                            }
                        }
                    }
                    item {
                        Text(
                            text = "Developers",
                            modifier = Modifier.padding(horizontal = 12.dp)
                                .padding(top = 24.dp, bottom = 12.dp)
                                .fillMaxWidth(),
                            style = MaterialTheme.typography.headlineMedium
                        )
                    }
                    items(data.developers) {
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp).padding(bottom = 8.dp)
                                .fillMaxWidth()
                        ) {
                            AsyncImage(
                                model = it.image, contentDescription = null,
                                modifier = Modifier.size(120.dp).background(
                                    color = Color.Transparent,
                                    shape = RoundedCornerShape(12.dp)
                                ).clip(RoundedCornerShape(12.dp)),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(Modifier.width(8.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = it.name,
                                    style = MaterialTheme.typography.titleSmall,
                                    modifier = Modifier.padding(end = 8.dp)
                                )
                                Spacer(Modifier.height(8.dp))
                                Text(
                                    text = "Game Count : ${it.gameCount}",
                                    style = MaterialTheme.typography.bodySmall,
                                )
                            }
                        }
                    }
                }

                Row(modifier = Modifier.padding(top = innerPadding.calculateTopPadding())
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()) {
                    IconButton(
                        onClick = onBackCLick,
                        modifier = Modifier.background(color = Color.White, shape = CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = null,
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                    Spacer(Modifier.weight(1f))
                    IconButton(
                        onClick = { onSaveCLick(data.id, data.name, data.backgroundImage) },
                        modifier = Modifier.background(color = Color.White, shape = CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = null,
                            modifier = Modifier.padding(4.dp)
                        )
//                    Text("Fav", modifier = Modifier.padding(4.dp))
                    }
                    /*Spacer(Modifier.width(12.dp))
                    IconButton(
                        onClick = { onDeleteCLick(data.id) },
                        modifier = Modifier.background(color = Color.White, shape = CircleShape)
                    ) {
                        Text("Delete", modifier = Modifier.padding(4.dp))
                    }*/
                    }
            }
        }
    }
}