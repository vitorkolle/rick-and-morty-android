package com.example.rickandmorty.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.rickandmorty.model.Character
import com.example.rickandmorty.model.Episode
import com.example.rickandmorty.service.RetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    Column {
        AsyncImage(model = character.value.image, contentDescription = "", modifier = Modifier.padding(top = 50.dp))
        Text(text = character.value.name)
        Text(text = character.value.origin?.name ?: "")
        Text(text = character.value.species)
        Text(text = character.value.gender)
        Text(text = character.value.status)
        Text(text = character.value.type)
        Text(text = character.value.location?.name ?: "")
    }


}

@Composable
fun CriarCardEpisodio(modifier: Modifier = Modifier) {
    val episode = remember {
        mutableStateOf(Episode())
    }
}