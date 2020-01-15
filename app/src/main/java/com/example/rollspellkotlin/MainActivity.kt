package com.example.rollspellkotlin

import com.example.rollspellkotlin.Myapp.Companion.player
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rollspellkotlin.Adapter.GamingBoardAdapter
import kotlinx.android.synthetic.main.activity_main.*
import android.view.animation.AnimationUtils
import android.content.Intent
import com.example.rollspellkotlin.Models.*


class MainActivity : AppCompatActivity() {

    var userPosition = 0
    val weapon = Weapon("excalibur", 10.0, 500)
    val armor = Armor("gold chestplate", 10.0, 500)
    val spell1 = Spell("Fireball", 20.0, 0.0, "fais de gros degats")
    val spell2 = Spell("benedixion des mains", 0.0, 20.0, "vous heal bcp")


    fun addList(): ArrayList<Spell> {
        var spellList = ArrayList<Spell>()
        spellList.add(spell1)
        spellList.add(spell2)
        return spellList
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Myapp.createPlayer("alpheonix",weapon,100.0,armor, 0,addList())
        println("debut game ${player.life}")
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
                "monster" -> startFight(player)
                "coin" -> coins_text_view.text = "55"
                "auberge" -> health_text_view.text = "60"
            }
        }
    }


    override fun onStart() {
        super.onStart()
        println("start")
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
        squaresList.add(GamingBoardSquare("monster"))
        squaresList.add(GamingBoardSquare("monster"))
        squaresList.add(GamingBoardSquare("monster"))
        squaresList.add(GamingBoardSquare("monster"))
        squaresList.add(GamingBoardSquare("monster"))
        return squaresList;
    }

    fun dicePier(): Int {
        return (1..6).random()
    }

    fun startFight(player:Player){
        val intent = Intent(this, ArenaScreen::class.java)


        startActivity(intent)
    }

}
