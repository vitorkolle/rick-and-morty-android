package com.example.rickandmorty.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.rickandmorty.R
import com.example.rickandmorty.model.Character
import com.example.rickandmorty.model.Result
import com.example.rickandmorty.service.RetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun CharactersList(modifier: Modifier = Modifier, controleDeNavegacao: NavHostController) {

    var characterList by remember {
        mutableStateOf(listOf<Character>())
    }

    //conectar-se com a API
    val callCharacters = RetrofitFactory()
        .getCharacterService().getAllCharacters()

    callCharacters.enqueue(object : Callback<Result>{
        override fun onResponse(p0: Call<Result>, p1: Response<Result>) {
            characterList = p1.body()!!.results
        }

        override fun onFailure(p0: Call<Result>, p1: Throwable) {
            TODO("Not yet implemented")
        }
    })

    
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color(0xFF0FCEB1)
    ) {
        Image(
            painter = painterResource(id = R.drawable.wallpaper_rick_and_morty),
            contentDescription = "",
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Rick & Morty",
                color = Color(0xFF0072FF),
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,

            )
            Spacer(modifier = Modifier.height(24.dp))
            LazyColumn {
                items(characterList){ it ->
                    CharacterCard(character = it, controleDeNavegacao)
                }
            }
        }
    }
}

@Composable
fun CharacterCard(character: Character, controleDeNavegacao: NavHostController) {
    Button(
        onClick = {
            controleDeNavegacao.navigate("DetalhesPersonagem/${character.id}")
        },
        colors = ButtonColors(
            containerColor = Color.Transparent,
            contentColor = Color.Transparent,
            disabledContentColor = Color.Transparent,
            disabledContainerColor = Color.Transparent
        )
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(bottom = 6.dp),
            colors = CardDefaults
                .cardColors(
                    containerColor = Color(0xFF188F1A)
                ),
            border = BorderStroke(width = 2.dp, color = Color.White)
        ) {
            Row(
                modifier = Modifier.fillMaxSize()
            ) {
                Card(
                    modifier = Modifier
                        .size(100.dp)
                ) {
                    AsyncImage(
                        model = character?.image,
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 16.dp)
                ) {
                    Text(
                        text = character?.name!!,
                        fontSize = 24.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold

                    )
                    Text(
                        text = character.species,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CharacterCardPreview() {
   // CharacterCard(character = null)
}

@Preview(showSystemUi = true)
@Composable
private fun CharactersListPreview() {
   // CharactersList()
}