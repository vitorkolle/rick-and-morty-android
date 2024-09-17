package com.example.rickandmorty.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.rickandmorty.R
import com.example.rickandmorty.model.Character
import com.example.rickandmorty.model.Episode
import com.example.rickandmorty.service.RetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

@Composable
fun CharactersDetail(modifier: Modifier = Modifier, id: Int) {

    val character = remember {
        mutableStateOf(Character())
    }

        //efetuar a chamada para a api
        val callCharacterById = RetrofitFactory().getCharacterService().getCharacterById(id)



        callCharacterById.enqueue(object : Callback<Character> {
            override fun onResponse(p0: Call<Character>, p1: Response<Character>) {
                character.value = p1.body()!!
                Log.i(
                    "DV!",
                    "Deu Certo"
                )
            }

            override fun onFailure(p0: Call<Character>, p1: Throwable) {
                TODO("Not yet implemented")
            }

        })

    val episode = remember {
        mutableStateOf(Episode())
    }


    fun getEpisodeById(id: Int){
        val callEpisodeById = RetrofitFactory().getEpisodeService().getEpisodeById(id)

        callEpisodeById.enqueue(object : Callback<Episode> {
            override fun onResponse(p0: Call<Episode>, p1: Response<Episode>) {
                episode.value = p1.body()!!
                Log.i(
                    "DV!",
                    "Deu Certo"
                )

            }

            override fun onFailure(p0: Call<Episode>, p1: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }


    val listOfEpisodes = character.value.episode
    listOfEpisodes?.forEach {
        getEpisodeById(it.substring(40).toInt())
    }


    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFF188F1A)
            )
    ) {

        AsyncImage(
            model = character.value.image,
            contentDescription = "",
            modifier = Modifier
                .padding(10.dp)
                .size(120.dp)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Text(
                text = character.value.name,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )

            Text(
                text = character.value.origin?.name ?: "Null",
                color = Color.White,
                fontSize = 15.sp
            )

            Text(
                text = character.value.species,
                color = Color.White,
                fontSize = 15.sp
            )

            Text(
                text = character.value.gender,
                color = Color.White,
                fontSize = 15.sp
            )

            Text(
                text = character.value.status,
                color = Color.White,
                fontSize = 15.sp
            )

            Text(
                text = character.value.type?: "Null",
                color = Color.White,
                fontSize = 15.sp
            )

            Text(
                text = character.value.location?.name ?: "Null",
                color = Color.White,
                fontSize = 15.sp
            )
        }

    }

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {

    }


}

 @Composable
fun CriarCardEpisodio(modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
    ){

    }
}

@Preview
@Composable
private fun PreviewCardEpisodio(){
    CriarCardEpisodio()
}
