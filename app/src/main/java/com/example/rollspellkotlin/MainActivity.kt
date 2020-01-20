package com.example.rollspellkotlin

import com.example.rollspellkotlin.Myapp.Companion.player
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rollspellkotlin.Adapter.GamingBoardAdapter
import kotlinx.android.synthetic.main.activity_main.*
import android.view.animation.AnimationUtils
import android.content.Intent
import android.widget.Toast
import com.example.rollspellkotlin.Models.*


class MainActivity : AppCompatActivity() {

    var userPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        coins_text_view.text = player.gold.toString()
        health_text_view.text = player.life.toString()
        shield_text_view.text = player.armor.defense.toString()
        attack_text_view.text = player.weapon.damage.toString()
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
                "coin" -> coins_text_view.text = player.gold.toString()
                "auberge" -> health_text_view.text = player.life.toString()
                "boss" -> startFightBoss()
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
        squaresList.add(GamingBoardSquare("monster"))
        squaresList.add(GamingBoardSquare("monster"))
        squaresList.add(GamingBoardSquare("monster"))
        squaresList.add(GamingBoardSquare("monster"))
        squaresList.add(GamingBoardSquare("monster"))
        squaresList.add(GamingBoardSquare("boss"))
        squaresList.add(GamingBoardSquare("boss"))
        squaresList.add(GamingBoardSquare("boss"))
        squaresList.add(GamingBoardSquare("boss"))
        squaresList.add(GamingBoardSquare("boss"))
        return squaresList;
    }

    override fun onRestart() {
        super.onRestart()

        if(player.life <= 0){

            finish()
        }

        coins_text_view.text = player.gold.toString()
        health_text_view.text = player.life.toString()

    }

    fun dicePier(): Int {
        return (1..6).random()
    }

    fun startFight(){
        val intent = Intent(this, ArenaScreen::class.java)
        startActivity(intent)
    }
    fun startFightBoss(){
        val intent = Intent(this, BossBattleActivity::class.java)
        startActivity(intent)
    }

}
