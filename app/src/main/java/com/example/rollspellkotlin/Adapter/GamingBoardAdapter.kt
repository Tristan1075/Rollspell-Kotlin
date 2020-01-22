package com.example.rollspellkotlin.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rollspellkotlin.Models.GamingBoardSquare
import com.example.rollspellkotlin.R
import kotlinx.android.synthetic.main.game_bord_square.view.*
import kotlin.collections.ArrayList

class GamingBoardAdapter(var squares: ArrayList<GamingBoardSquare>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var gamerPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.game_bord_square, parent, false)
        return Item(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as Item).initGameSquare(squares[position], position + 1)
        if(position == gamerPosition){
            holder.itemView.setBackgroundResource(R.drawable.card_border_active)
        } else {
            holder.itemView.setBackgroundResource(R.drawable.card_border)
        }
    }

    override fun getItemCount(): Int = squares.size

    fun movePlayerPosition(newPosition: Int){
        gamerPosition = newPosition
    }

    fun getSquareType(position: Int): GamingBoardSquare {
        return squares[position]
    }

    class Item(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun initGameSquare(square: GamingBoardSquare, position: Int) {
            when (square.type) {
                "boss" -> itemView.gaming_board_image_view.setImageResource(R.drawable.boss)
                "monster" -> itemView.gaming_board_image_view.setImageResource(R.drawable.fight)
                "coin" -> itemView.gaming_board_image_view.setImageResource(R.drawable.coin)
                "auberge" -> itemView.gaming_board_image_view.setImageResource(R.drawable.auberge)
            }
            itemView.gaming_board_square_index.text = position.toString()
        }
    }
}