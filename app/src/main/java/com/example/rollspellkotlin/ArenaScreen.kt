package com.example.rollspellkotlin

import com.example.rollspellkotlin.Myapp.Companion.player
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.example.rollspellkotlin.Models.Monster
import com.example.rollspellkotlin.Models.Player
import kotlinx.android.synthetic.main.activity_arena_screen.*


class ArenaScreen : AppCompatActivity() {

    val monster = Monster("angry dwarf",10.0,50.0,10)


    private var mLastClickTime: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_arena_screen)
        println("begining ${player.life}")

        PlayerLifeTextView.text = player.life.toString()
        enemyNameTextView.text = monster.name
        enemyLifeTextView.text = monster.life.toString()


        PlayerBasicAttackImageView.setOnClickListener {
            if (SystemClock.elapsedRealtime() - mLastClickTime < 4000){
                return@setOnClickListener
            }
            mLastClickTime = SystemClock.elapsedRealtime()
            Toast.makeText(this,"vous faites une attaque basique",Toast.LENGTH_SHORT).show()
            val aniSlideup = AnimationUtils.loadAnimation(applicationContext, R.anim.slide_up)
            PlayerAvatarImageView.startAnimation(aniSlideup)
            monster.life -= player.weapon.damage
            enemyLifeTextView.text = monster.life.toString()
            if (monster.life <= 0.0){
                deathMonster(player)
            }
            if(player.life <=0.0){
                deathPlayer(player)
            }else{
                monsterDamage(player)
            }

            }

        PlayerSpell1ImageView.setOnClickListener{

            if (SystemClock.elapsedRealtime() - mLastClickTime < 4000){
                return@setOnClickListener
            }
            mLastClickTime = SystemClock.elapsedRealtime()
            val aniSlideup = AnimationUtils.loadAnimation(applicationContext, R.anim.slide_up)
            PlayerAvatarImageView.startAnimation(aniSlideup)
            Toast.makeText(this,player.spell[0].name,Toast.LENGTH_SHORT).show()
            monster.life -= player.spell[0].damage
            enemyLifeTextView.text = monster.life.toString()
            if(player.life< 100){
                player.life += player.spell[0].heal
                if (player.life >100){
                    player.life = 100.0
                }
            }
            PlayerLifeTextView.text = player.life.toString()
            if (monster.life <= 0.0){
                deathMonster(player)
            }else{
                monsterDamage(player)
            }


        }

        PlayerSpell2ImageView.setOnClickListener {
            if (SystemClock.elapsedRealtime() - mLastClickTime < 4000){
                return@setOnClickListener
            }
            mLastClickTime = SystemClock.elapsedRealtime()
            val aniSlideup = AnimationUtils.loadAnimation(applicationContext, R.anim.slide_up)
            PlayerAvatarImageView.startAnimation(aniSlideup)
            Toast.makeText(this,player.spell[1].name,Toast.LENGTH_SHORT).show()
            monster.life -= player.spell[1].damage
            enemyLifeTextView.text = monster.life.toString()
            if(player.life< 100){
                player.life += player.spell[1].heal
                if (player.life >100){
                    player.life = 100.0
                }
            }
            PlayerLifeTextView.text = player.life.toString()
            if (monster.life <= 0.0){
                deathMonster(player)
            }else{
                monsterDamage(player)
            }
        }


    }
    fun monsterDamage(player:Player){
        PlayerBasicAttackImageView.postDelayed(
            {
                val aniSlide = AnimationUtils.loadAnimation(applicationContext, R.anim.slide_down)
                enemyAvatarImageView.startAnimation(aniSlide)
                var critDamage:Double = monster.damage
                val rand = (1..100).random()

                if(rand<monster.critChance){
                    critDamage *= 1.5
                    Toast.makeText(this,"Il fait un coup critique",Toast.LENGTH_SHORT).show()

                }

                player.life = player.life - (critDamage - (critDamage * (player.armor.defense / 100)))
                PlayerLifeTextView.text = player.life.toString()
                println("after damage ${player.life}")
            },
            2000 // value in milliseconds
        )
    }

    fun deathPlayer(player: Player){

    println("${player.name} you died")

    }

    private fun deathMonster(player:Player){
        PlayerBasicAttackImageView.postDelayed(
            {
        player.gold += 50
                println("end ${player.life}")
        finish()
            },
            1500 // value in milliseconds
        )
    }



}
