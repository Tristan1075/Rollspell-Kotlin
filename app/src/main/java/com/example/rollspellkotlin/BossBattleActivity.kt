package com.example.rollspellkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.example.rollspellkotlin.Models.*
import kotlinx.android.synthetic.main.activity_arena.*

class BossBattleActivity : AppCompatActivity() {

    val monster = Monster("King dwarf",15.0,70.0,2)


    private var mLastClickTime: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_boss_battle)


        PlayerLifeTextView.text = Myapp.player.life.toString()
        enemyNameTextView.text = monster.name
        enemyLifeTextView.text = monster.life.toString()


        PlayerBasicAttackImageView.setOnClickListener {

            if (SystemClock.elapsedRealtime() - mLastClickTime < 4000){
                return@setOnClickListener
            }

            mLastClickTime = SystemClock.elapsedRealtime()
            Toast.makeText(this,"vous faites une attaque basique", Toast.LENGTH_SHORT).show()
            val aniSlideup = AnimationUtils.loadAnimation(applicationContext, R.anim.slide_up)
            PlayerAvatarImageView.startAnimation(aniSlideup)
            monster.life -= Myapp.player.weapon.damage
            enemyLifeTextView.text = monster.life.toString()


            if (monster.life <= 0.0){
                deathMonster(Myapp.player)
            }else if(Myapp.player.life <=0.0){
                deathPlayer(Myapp.player)
            }else{
                monsterDamage(Myapp.player)
            }


        }

        PlayerSpell1ImageView.setOnClickListener{

            if (SystemClock.elapsedRealtime() - mLastClickTime < 4000){
                return@setOnClickListener
            }

            mLastClickTime = SystemClock.elapsedRealtime()
            val aniSlideup = AnimationUtils.loadAnimation(applicationContext, R.anim.slide_up)
            PlayerAvatarImageView.startAnimation(aniSlideup)
            Toast.makeText(this, Myapp.player.spell[0].name, Toast.LENGTH_SHORT).show()
            monster.life -= Myapp.player.spell[0].damage

            if(monster.life < 0){
                monster.life = 0.0
            }

            enemyLifeTextView.text = monster.life.toString()

            if(Myapp.player.life< 100){
                Myapp.player.life += Myapp.player.spell[0].heal
                if (Myapp.player.life >100){
                    Myapp.player.life = 100.0
                }

            }

            PlayerLifeTextView.text = Myapp.player.life.toString()

            if (monster.life <= 0.0){

                deathMonster(Myapp.player)

            }else if(Myapp.player.life <=0.0){
                deathPlayer(Myapp.player)
            }else{
                monsterDamage(Myapp.player)
            }

        }

        PlayerSpell2ImageView.setOnClickListener {

            if (SystemClock.elapsedRealtime() - mLastClickTime < 4000){
                return@setOnClickListener
            }

            mLastClickTime = SystemClock.elapsedRealtime()
            val aniSlideup = AnimationUtils.loadAnimation(applicationContext, R.anim.slide_up)
            PlayerAvatarImageView.startAnimation(aniSlideup)
            Toast.makeText(this, Myapp.player.spell[1].name, Toast.LENGTH_SHORT).show()
            monster.life -= Myapp.player.spell[1].damage
            enemyLifeTextView.text = monster.life.toString()

            if(Myapp.player.life< 100){
                Myapp.player.life += Myapp.player.spell[1].heal

                if (Myapp.player.life >100){

                    Myapp.player.life = 100.0
                }

            }

            PlayerLifeTextView.text = Myapp.player.life.toString()


            if (monster.life <= 0.0){

                deathMonster(Myapp.player)

            }else if(Myapp.player.life <=0.0){
                deathPlayer(Myapp.player)
            }else{
                monsterDamage(Myapp.player)
            }
        }


    }
    fun monsterDamage(player: Player){
        PlayerBasicAttackImageView.postDelayed(
            {
                val aniSlide = AnimationUtils.loadAnimation(applicationContext, R.anim.slide_down)
                enemyAvatarImageView.startAnimation(aniSlide)

                val randCrit = (1..100).random()
                val randAtk = (1..3).random()

                when(randAtk) {
                    1 -> spell1Boss(randCrit,player)
                    2 -> spell2Boss(randCrit,player)
                    3 -> baseAttackBoss(randCrit,player)
                }


                if(Myapp.player.life <=0.0){
                    deathPlayer(Myapp.player)
                }
            },
            2000 // value in milliseconds
        )
    }

    fun spell1Boss(randCrit:Int,player:Player){
        Toast.makeText(this,"Foudre", Toast.LENGTH_SHORT).show()
        var critDamage:Double = monster.damage
        if(randCrit<monster.critChance){
            critDamage *= 1.5
            Toast.makeText(this,"Il fait un coup critique", Toast.LENGTH_SHORT).show()
        }
        player.life = player.life - (critDamage *1.5 - (critDamage * (player.armor.defense / 100)))
        PlayerLifeTextView.text = player.life.toString()
        if(Myapp.player.life <=0.0){
            deathPlayer(Myapp.player)
        }
    }

    fun spell2Boss(randCrit:Int,player:Player){
        Toast.makeText(this,"Attaque de courone", Toast.LENGTH_SHORT).show()
        var critDamage:Double = monster.damage
        if(randCrit<monster.critChance){
            critDamage *= 1.5
            Toast.makeText(this,"Il fait un coup critique", Toast.LENGTH_SHORT).show()
        }
        player.life = player.life - (critDamage*2 - (critDamage * (player.armor.defense / 100)))
        PlayerLifeTextView.text = player.life.toString()
        if(Myapp.player.life <=0.0){
            deathPlayer(Myapp.player)
        }
    }

    fun baseAttackBoss(randCrit:Int,player:Player){
        var critDamage:Double = monster.damage
        if(randCrit<monster.critChance){
            critDamage *= 1.5
            Toast.makeText(this,"Il fait un coup critique", Toast.LENGTH_SHORT).show()

        }
        player.life = player.life - (critDamage - (critDamage * (player.armor.defense / 100)))
        PlayerLifeTextView.text = player.life.toString()
        if(Myapp.player.life <=0.0){
            deathPlayer(Myapp.player)
        }
    }

    fun deathPlayer(player: Player){
    finish()


    }
    fun createWeapon():Items{
        var item:Items
        item = Weapon((1..50).random().toDouble(),"test", (20..100).random())
        PlayerBasicAttackImageView.postDelayed({
            Toast.makeText(this,"vous remportez ${item.name}", Toast.LENGTH_SHORT).show()
        },500)
        return item
    }
    fun createArmor():Items{
        var item:Items
        item = Armor("test",(1..50).random().toDouble(), (20..100).random())
        PlayerBasicAttackImageView.postDelayed({
            Toast.makeText(this,"vous remportez ${item.name}", Toast.LENGTH_SHORT).show()
        },500)

        return item
    }

    private fun deathMonster(player: Player){
        PlayerBasicAttackImageView.postDelayed(
            {
                player.gold += 50

                var rand = (1..2).random()

                when(rand){
                    1 -> player.items.add(createWeapon())
                    2 -> player.items.add(createArmor())
                }
                finish()
            },
            1500 // value in milliseconds
        )
    }


}
