package com.example.rickandmorty

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rickandmorty.model.Character
import com.example.rickandmorty.service.RetrofitFactory
import com.example.rickandmorty.ui.theme.RickAndMortyTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()
        setContent {
            RickAndMortyTheme{
                ListAllCharacters()
                }
            }
        }
    }

@Composable
fun ListAllCharacters(modifier: Modifier = Modifier) {

    val characterList = remember {
        mutableStateOf(listOf<Character>())
    }

    val character = remember {
        mutableStateOf(Character())
    }

    val id = remember {
        mutableStateOf("")
    }

    Button(modifier = Modifier.padding(top = 55.dp), onClick = {
        //efetuar a chamada para a api

        val callCharacterById = RetrofitFactory().getCharacterService().getCharacterById(id.value.toInt())

        callCharacterById.enqueue(object : Callback<Character>{
            override fun onResponse(p0: Call<Character>, p1: Response<Character>) {
                character.value = p1.body()!!
                Log.i(
                    "RICK_MORTY",
                    "${character.value.name} - ${character.value.origin!!.name} - ${character.value.gender}"
                )
            }

            override fun onFailure(p0: Call<Character>, p1: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }) {}

    Column {
        OutlinedTextField(value = id.value, onValueChange = {id.value = it})
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
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RickAndMortyTheme {
        Greeting("Android")
    }
}