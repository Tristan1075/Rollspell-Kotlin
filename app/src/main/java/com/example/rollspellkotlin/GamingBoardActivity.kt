package com.example.rollspellkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rollspellkotlin.Adapter.GamingBoardAdapter
import kotlinx.android.synthetic.main.activity_main.*
import android.view.animation.AnimationUtils
import android.content.Intent
import android.os.Handler
import android.view.View
import com.example.rollspellkotlin.Models.*
import com.example.rollspellkotlin.Myapp.Companion.player
import android.view.MotionEvent
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T


class GamingBoardActivity : AppCompatActivity() {

    var userPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        coins_text_view.text = player.gold.toString()
        health_text_view.text = (player.life.toInt()).toString()
        shield_text_view.text = player.equipments.getArmor().toString()
        attack_text_view.text = player.weapon.attack.toString()
        gaming_board_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
        gaming_board_recycler_view.adapter = GamingBoardAdapter(getLists())
        gaming_board_recycler_view.setOnTouchListener { _, _ -> true }
        dice_image_view.setOnClickListener {
            val dice = dicePier()
            // Dice animation
            val aniSlide = AnimationUtils.loadAnimation(applicationContext, R.anim.slide_up)
            dice_image_view.startAnimation(aniSlide)

            // Update player position
            userPosition += dice
            gaming_board_recycler_view.smoothScrollToPosition(userPosition+1)
            (gaming_board_recycler_view.adapter as GamingBoardAdapter).movePlayerPosition(userPosition)
            (gaming_board_recycler_view.adapter as GamingBoardAdapter).notifyItemChanged(userPosition)
            Handler().postDelayed({
                val newSquare = getSquareType()
                when (newSquare.type) {
                    "monster" -> startFight()
                    "coin" -> coins_text_view.text = player.gold.toString()
                    "auberge" -> health_text_view.text = player.life.toString()
                    "boss" -> startFightBoss()
                }
            }, 1200)
        }
        inventory_button_text_view.setOnClickListener {
            startActivity(Intent(this, InventoryActivity::class.java))
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
        squaresList.add(GamingBoardSquare("coin"))
        squaresList.add(GamingBoardSquare("coin"))
        squaresList.add(GamingBoardSquare("coin"))
        squaresList.add(GamingBoardSquare("coin"))
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
        if (player.life <= 0) finish()
        coins_text_view.text = player.gold.toString()
        health_text_view.text = player.life.toString()
    }

    fun dicePier(): Int {
        return (1..6).random()
    }

    fun startFight(){
        val intent = Intent(this, ArenaActivity::class.java)
        startActivity(intent)
    }
    fun startFightBoss(){
        val intent = Intent(this, BossBattleActivity::class.java)
        startActivity(intent)
    }

}
