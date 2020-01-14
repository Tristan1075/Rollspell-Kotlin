package com.example.rollspellkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.view.Window
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.example.rollspellkotlin.Models.Armor
import com.example.rollspellkotlin.Models.Monster
import com.example.rollspellkotlin.Models.Player
import com.example.rollspellkotlin.Models.Weapon
import kotlinx.android.synthetic.main.activity_arena_screen.*
import kotlinx.android.synthetic.main.activity_main.*
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import java.util.*


class ArenaScreen : AppCompatActivity() {

    val monster = Monster("angry dwarf",10.0,50.0,10)


    private var mLastClickTime: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        val player = intent.getSerializableExtra("player") as Player
        super.onCreate(savedInstanceState)
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);

//Remove notification bar
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_arena_screen)

        PlayerLifeTextView.text = player.life.toString()
        enemyNameTextView.text = monster.name
        enemyLifeTextView.text = monster.life.toString()


        PlayerBasicAttackImageView.setOnClickListener {
            if (SystemClock.elapsedRealtime() - mLastClickTime < 4000){
                return@setOnClickListener
            }
            mLastClickTime = SystemClock.elapsedRealtime();
            Toast.makeText(this,"vous faites une attaque basique",Toast.LENGTH_SHORT).show()
            val aniSlideup = AnimationUtils.loadAnimation(applicationContext, R.anim.slide_up)
            PlayerAvatarImageView.startAnimation(aniSlideup)
            monster.life -= player.weapon.damage
            enemyLifeTextView.text = monster.life.toString()
            println("monster")
            println(monster.life)
            if (monster.life <= 0.0){
                deathmonster(player)
            }
            monsterDamage(player)

            }



    }
    fun monsterDamage(player:Player){
        PlayerBasicAttackImageView.postDelayed(
            {
                println("yoyo")
                val aniSlide = AnimationUtils.loadAnimation(applicationContext, R.anim.slide_down)
                enemyAvatarImageView.startAnimation(aniSlide)
                var critDamage:Double = monster.damage
                val rand = (1..100).random()

                if(rand<monster.critChance){
                    critDamage *= 1.5
                    Toast.makeText(this,"Il fait un coup critique",Toast.LENGTH_SHORT).show()

                }
                println(player.life - critDamage - (critDamage * (player.armor.defense / 100)))
                player.life = player.life - (critDamage - (critDamage * (player.armor.defense / 100)))
                PlayerLifeTextView.text = player.life.toString()
            },
            2000 // value in milliseconds
        )
    }

    fun deathPlayer(){

    }

    fun deathmonster(player:Player){
        Handler().postDelayed(
            {
        player.gold += 50
        print(player.gold)
        finish()
            },
            1500 // value in milliseconds
        )
    }



}
