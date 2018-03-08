package com.delacerna.pokeapi


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View.INVISIBLE
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.net.URL


class MainActivity : AppCompatActivity() {

    private val POKEMON_KEY_ID = "id"
    private val POKEMON_KEY_NAME = "name"
    private val POKEMON_KEY_SPRITES = "sprites"
    private val POKEMON_KEY_FRONT_DEFAULT = "front_default"
    private val addPokemon = ArrayList<Poke>()
    private val url = "https://pokeapi.co/api/v2/pokemon/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView1.layoutManager = LinearLayoutManager(this)
        for (i in 1..20) {
            doAsync {
                val resultJson = URL(url + i).readText()
                uiThread {
                    val jsonObject = JSONObject(resultJson)
                    val pokeID = jsonObject.getInt(POKEMON_KEY_ID)
                    val pokeName = jsonObject.getString(POKEMON_KEY_NAME)
                    val sprites = jsonObject.getString(POKEMON_KEY_SPRITES)
                    val jsonObject2 = JSONObject(sprites)
                    val pokeSprite = jsonObject2.getString(POKEMON_KEY_FRONT_DEFAULT)

                    addPokemon.add(Poke(pokeID, pokeName, Sprite(pokeSprite)))
                    recyclerView1.adapter = PokeAdapter(addPokemon)
                    txtPokeUpdate.text = "Get Ready! Your " + addPokemon.size.toString() + " Pokemons Will Come!"

                    if (addPokemon.size == 20) {
                        progressBar.visibility = INVISIBLE
                    }

                }
            }

        }
    }

}
