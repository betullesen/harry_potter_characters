package com.betulesen.harrypotterapp.view

import android.icu.text.StringSearch
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.work.ListenableWorker.Result.Retry
import com.betulesen.harrypotterapp.model.HarryPotterList
import com.betulesen.harrypotterapp.model.HarryPotterListItem
import com.betulesen.harrypotterapp.viewmodel.CharacterListViewModel
import org.jetbrains.annotations.Async

import coil.compose.rememberImagePainter
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size


@Composable
fun CharacterListScreen(
    viewModel: CharacterListViewModel = hiltViewModel(),
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxSize()

    ) {
        Column {
            Text("Harry Potter",modifier= Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 20.dp, end = 20.dp),
                textAlign = TextAlign.Center,
                fontSize = 44.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.tertiary
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(

                text = "Characters",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.align(Alignment.CenterHorizontally)

            )
            
            Spacer(modifier = Modifier.height(20.dp))
            //Search kısmı
            SearchBar(
                hint = "Karakterleri arayabilirsiniz",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ){
                viewModel.searchCharacterList(it)
            }

            Spacer(modifier = Modifier.height(10.dp))
            //Liste Ekranı
            CharacterList(navController = navController)
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint : String = "",
    onSearch: (String) -> Unit = {}
) {

    var text by remember {
        mutableStateOf("")
    }

    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 10.dp) ){
        BasicTextField(value = text, onValueChange = {
            text = it
            onSearch(it)
        },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(8.dp, CircleShape)
                .border(2.dp, MaterialTheme.colorScheme.tertiary, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 16.dp)
                .onFocusChanged {
                    isHintDisplayed = it.isFocused != true && text.isEmpty()
                })
        if(isHintDisplayed){
            Text(text=hint,
                color = Color.LightGray,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 14.dp))
        }
    }
}

@Composable
fun CharacterList(navController: NavController,viewModel: CharacterListViewModel= hiltViewModel()) {
    val characterList by remember { viewModel.characterList }
    val errorMessage by remember { viewModel.errorMessage }
    val isLoading by remember { viewModel.isLoading }

    CharacterListView(chacarters = characterList,navController=navController)

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()){
        if(isLoading){
            CircularProgressIndicator()
        }
        if(errorMessage.isNotEmpty()){
            RetryView(error = errorMessage) {
                viewModel.loadCharacters()
            }

        }
    }
    
}

@Composable
fun CharacterListView(
    chacarters: List<HarryPotterListItem>,
    navController: NavController) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){

        items(chacarters){chacarter ->
            CharacterRow(navController = navController,chacarter=chacarter)
        }
    }
    
}
@Composable
fun CharacterRow(
    navController: NavController,
    chacarter: HarryPotterListItem
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .clickable { navController.navigate("character_detail_screen/${chacarter.name}") },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.elevatedCardElevation(8.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Resim
            Image(
                painter = rememberImagePainter(data = chacarter.image),
                contentDescription = chacarter.name,
                modifier = Modifier
                    .size(130.dp)
                    .padding(8.dp)
                    .align(Alignment.CenterHorizontally)
                    .clip(RoundedCornerShape(8.dp))
            )

            // Character name (headline)
            chacarter.name?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.CenterHorizontally)


                )
            }

            // Actor
            chacarter.actor?.let {
                Text(
                    text = "Actor: $it",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.DarkGray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            // Patronus
            chacarter.patronus?.let {
                Text(
                    text = "Patronus: $it",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.DarkGray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            // House
            chacarter.house?.let {
                Text(
                    text = "House: $it",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.DarkGray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

        }
    }
}

@Composable
fun RetryView(
    error : String,
    onRetry: () -> Unit
) {
    Column() {
        Text(error, color = Color.Black, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {
            onRetry
        }, modifier = Modifier.align(Alignment.CenterHorizontally)){
            Text(text = "Yeniden Deneyiniz")
        }
    }

}