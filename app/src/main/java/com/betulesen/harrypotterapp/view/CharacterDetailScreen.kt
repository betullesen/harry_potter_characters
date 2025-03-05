package com.betulesen.harrypotterapp.view

import android.net.wifi.aware.Characteristics
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.betulesen.harrypotterapp.model.HarryPotterList
import com.betulesen.harrypotterapp.model.HarryPotterListItem
import com.betulesen.harrypotterapp.util.Resource
import com.betulesen.harrypotterapp.viewmodel.CharacterDetailViewModel

@Composable
fun CharacterDetailScreen(
    id: String,
    navController: NavController,
    viewModel: CharacterDetailViewModel = hiltViewModel()
) {
    val characterItem = produceState<Resource<HarryPotterListItem>>(initialValue = Resource.Loading()) {
        value = viewModel.getCharactersDetail(id)
    }.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            when (characterItem) {
                is Resource.Success -> {
                    val character = characterItem.data
                    if (character != null) {

                        Image(
                            painter = rememberImagePainter(data = character.image),
                            contentDescription = character.name,
                            modifier = Modifier
                                .padding(16.dp)
                                .size(180.dp)
                                .clip(CircleShape)
                                .border(2.dp, MaterialTheme.colorScheme.tertiary, CircleShape)
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = character.name ?: "Unknown",
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.tertiary,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.weight(1f)
                            )
                            Text(
                                text = "(${character.actor ?: "Unknown"})",
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Light,
                                color = MaterialTheme.colorScheme.tertiary,
                                textAlign = TextAlign.End,
                                modifier = Modifier.weight(1f)
                            )
                        }


                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .border(1.dp, MaterialTheme.colorScheme.tertiary)
                                .fillMaxWidth()
                        ) {
                            TwoColumnInfoRow("House", character.house, "Ancestry", character.ancestry)
                            TwoColumnInfoRow("Patronus", character.patronus, "Species", character.species)
                            TwoColumnInfoRow("Date of Birth", character.dateOfBirth, "Year of Birth", character.yearOfBirth?.toString())
                            TwoColumnInfoRow("Eye Color", character.eyeColour, "Hair Color", character.hairColour)
                            TwoColumnInfoRow("Hogwarts Student", character.hogwartsStudent?.toString(), "Wizard", character.wizard?.toString())


                            CharacterInfoRow("Alive", character.alive?.toString())
                        }
                    }
                }

                is Resource.Error -> {
                    // Hata durumu
                    Text(
                        text = "Error: ${characterItem.message}",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(16.dp),
                        color = MaterialTheme.colorScheme.tertiary,
                        textAlign = TextAlign.Center
                    )
                }

                is Resource.Loading -> {
                    // Yükleniyor durumu
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            }
        }
    }
}


@Composable
fun TwoColumnInfoRow(label1: String, value1: String?, label2: String, value2: String?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp, horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$label1: ${value1 ?: "Unknown"}",
            modifier = Modifier.weight(1f),
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Start,
            color = MaterialTheme.colorScheme.tertiary
        )
        Text(
            text = "$label2: ${value2 ?: "Unknown"}",
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.End,
            color = MaterialTheme.colorScheme.tertiary
        )
    }
}


@Composable
fun CharacterInfoRow(label: String, value: String?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 24.dp)
    ) {
        Text(
            text = "$label: ${value ?: "Unknown"}",
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Start,
            color = MaterialTheme.colorScheme.tertiary
        )
    }
}
