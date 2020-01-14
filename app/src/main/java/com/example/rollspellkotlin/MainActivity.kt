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
import android.content.Intent
import com.example.rollspellkotlin.Models.Armor
import com.example.rollspellkotlin.Models.Player
import com.example.rollspellkotlin.Models.Weapon


class MainActivity : AppCompatActivity() {

    var userPosition = 0
    val weapon = Weapon("excalibur",10.0,500)
    val armor = Armor("gold chestplate",10.0,500)
    val player = Player("alpheonix",weapon,100.0,armor,0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gaming_board_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
        gaming_board_recycler_view.adapter = GamingBoardAdapter(this, getLists())

        dice_image_view.setOnClickListener {
            val aniSlide = AnimationUtils.loadAnimation(applicationContext, R.anim.slide_up)
            dice_image_view.startAnimation(aniSlide)
            userPosition += dicePier()
            gaming_board_recycler_view.smoothScrollToPosition(userPosition+1)
            (gaming_board_recycler_view.adapter as GamingBoardAdapter).movePlayerPosition(userPosition)

            val newSquare = getSquareType()
            when (newSquare.type) {
                "monster" -> startFight()
                "coin" -> coins_text_view.text = "55"
                "auberge" -> health_text_view.text = "60"
            }
        }
    }

    fun getSquareType(): GamingBoardSquare {
        return (gaming_board_recycler_view.adapter as GamingBoardAdapter).getSquareType(userPosition)
    }


    fun getLists(): ArrayList<GamingBoardSquare> {
        var squaresList = ArrayList<GamingBoardSquare>()
        squaresList.add(GamingBoardSquare("auberge"))
        squaresList.add(GamingBoardSquare("monster"))
        squaresList.add(GamingBoardSquare("auberge"))
        squaresList.add(GamingBoardSquare("coin"))
        squaresList.add(GamingBoardSquare("monster"))
        squaresList.add(GamingBoardSquare("monster"))
        squaresList.add(GamingBoardSquare("auberge"))
        squaresList.add(GamingBoardSquare("monster"))
        squaresList.add(GamingBoardSquare("coin"))
        squaresList.add(GamingBoardSquare("boss"))
        squaresList.add(GamingBoardSquare("monster"))
        squaresList.add(GamingBoardSquare("auberge"))
        squaresList.add(GamingBoardSquare("coin"))
        squaresList.add(GamingBoardSquare("monster"))
        squaresList.add(GamingBoardSquare("monster"))
        squaresList.add(GamingBoardSquare("auberge"))
        squaresList.add(GamingBoardSquare("monster"))
        squaresList.add(GamingBoardSquare("coin"))
        squaresList.add(GamingBoardSquare("boss"))
        return squaresList;
    }

    fun dicePier(): Int {
        return (1..6).random()
    }

    fun startFight(){
        val intent = Intent(this, ArenaScreen::class.java)
        intent.putExtra("player",player)

        startActivity(intent)
    }

}
