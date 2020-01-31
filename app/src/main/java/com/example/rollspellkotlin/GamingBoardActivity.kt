package com.example.rollspellkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rollspellkotlin.Adapter.GamingBoardAdapter
import kotlinx.android.synthetic.main.activity_main.*
import android.view.animation.AnimationUtils
import android.content.Intent
import android.os.Handler
import com.example.rollspellkotlin.Models.*
import com.example.rollspellkotlin.Myapp.Companion.player




class GamingBoardActivity : AppCompatActivity() {

    var userPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        updateStats()
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
                    "coin" ->  coins_text_view.text = gainCoins()
                    "auberge" -> health_text_view.text = regen()
                    "boss" -> startFightBoss()
                }
            }, 1200)
        }
        inventory_button_text_view.setOnClickListener {
            startActivity(Intent(this, InventoryActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        updateStats()
    }

    override fun onBackPressed() {
        return
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

    fun updateStats(){
        coins_text_view.text = player.gold.toString()
        health_text_view.text = (player.life).toString()
        shield_text_view.text = player.equipments.getArmor().toString()
        attack_text_view.text = player.equipments.getDamage().toString()
    }
    fun regen():String{

        if (player.life < 100) {
            player.life += 20
            if (player.life > 100) {
                player.life = 100
            }
        }
        return player.life.toString()
    }

    fun gainCoins():String {
        player.gold += 50
        return player.gold.toString()
    }

}
