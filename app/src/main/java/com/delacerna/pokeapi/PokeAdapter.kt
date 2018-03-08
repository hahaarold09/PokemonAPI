package com.delacerna.pokeapi

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.poke_list_layout.view.*

/**
 * Created by Harold on 3/7/2018.
 */
class PokeAdapter(val poke: ArrayList<Poke>) : RecyclerView.Adapter<CustomViewHolder>(){

    override fun getItemCount(): Int {
        return poke.size
    }
    override fun onBindViewHolder(holder: CustomViewHolder?, position: Int) {
        holder?.view?.txtPokeName?.text = poke[position].name

        val ImgView = holder?.view?.imgPoke
        Picasso.with(holder?.view?.context).load(poke[position].sprite.pokeString).into(ImgView)

        holder?.view?.listContainer?.setOnClickListener {
            println(poke[position].name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CustomViewHolder? {

        val view = LayoutInflater.from(parent?.context).inflate(R.layout.poke_list_layout, parent, false)
        return CustomViewHolder(view)
    }

}
class CustomViewHolder (var view: View): RecyclerView.ViewHolder(view){

}