package com.example.rollspellkotlin

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rollspellkotlin.Adapter.GamingBoardAdapter
import com.example.rollspellkotlin.Models.GamingBoardSquare
import kotlinx.android.synthetic.main.activity_main.*
import android.view.animation.AnimationUtils
import android.view.animation.Animation
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import kotlinx.android.synthetic.main.game_bord_square.*




class MainActivity : AppCompatActivity() {

    var userPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gaming_board_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
        gaming_board_recycler_view.adapter = GamingBoardAdapter(this, getLists())

        dice_image_view.setOnClickListener {
            val aniSlide = AnimationUtils.loadAnimation(applicationContext, R.anim.slide_up)
            dice_image_view.startAnimation(aniSlide)
            userPosition += dicePier()
            gaming_board_recycler_view.smoothScrollToPosition(userPosition)
            (gaming_board_recycler_view.adapter as GamingBoardAdapter).movePlayerPosition(userPosition)

        }
    }



    fun getLists(): ArrayList<GamingBoardSquare> {
        var squaresList = ArrayList<GamingBoardSquare>()
        squaresList.add(GamingBoardSquare("monster"))
        squaresList.add(GamingBoardSquare("auberge"))
        squaresList.add(GamingBoardSquare("coin"))
        squaresList.add(GamingBoardSquare("monster"))
        squaresList.add(GamingBoardSquare("monster"))
        squaresList.add(GamingBoardSquare("auberge"))
        squaresList.add(GamingBoardSquare("monster"))
        squaresList.add(GamingBoardSquare("coin"))
        squaresList.add(GamingBoardSquare("coin"))
        squaresList.add(GamingBoardSquare("monster"))
        squaresList.add(GamingBoardSquare("auberge"))
        squaresList.add(GamingBoardSquare("coin"))
        squaresList.add(GamingBoardSquare("monster"))
        squaresList.add(GamingBoardSquare("monster"))
        squaresList.add(GamingBoardSquare("auberge"))
        squaresList.add(GamingBoardSquare("monster"))
        squaresList.add(GamingBoardSquare("coin"))
        squaresList.add(GamingBoardSquare("coin"))
        return squaresList;
    }

    fun dicePier(): Int {
        return (1..6).random()
    }

}