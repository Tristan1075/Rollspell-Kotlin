package com.example.rollspellkotlin

import com.example.rollspellkotlin.Myapp.Companion.player
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.example.rollspellkotlin.Models.*
import kotlinx.android.synthetic.main.activity_arena.*
import android.os.Handler


class ArenaActivity : AppCompatActivity() {

    val monster = Monster("Angry dwarf", 10.0, 50.0, 10)


    private var mLastClickTime: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_arena)

        PlayerLifeTextView.text = player.life.toString()
        enemyNameTextView.text = monster.name
        enemyLifeTextView.text = monster.life.toString()
        val id = resources.getIdentifier("com.example.rollspellkotlin:drawable/${player.picture}", null, null)

        PlayerAvatarImageView.setImageResource(id)



        PlayerBasicAttackImageView.setOnClickListener {
            if (SystemClock.elapsedRealtime() - mLastClickTime < 4000) {
                return@setOnClickListener
            }
            mLastClickTime = SystemClock.elapsedRealtime()
            Toast.makeText(this, "vous faites une attaque basique", Toast.LENGTH_SHORT).show()
            val aniSlideup = AnimationUtils.loadAnimation(applicationContext, R.anim.slide_up)
            PlayerAvatarImageView.startAnimation(aniSlideup)
            monster.life -= player.equipments.getDamage()
            enemyLifeTextView.text = monster.life.toString()
            if (monster.life <= 0) {
                deathMonster(player)
            } else {
                monsterDamage(player)
            }

        }

        PlayerSpell1ImageView.setOnClickListener {

            if (SystemClock.elapsedRealtime() - mLastClickTime < 4000) {
                return@setOnClickListener
            }
            mLastClickTime = SystemClock.elapsedRealtime()
            val aniSlideup = AnimationUtils.loadAnimation(applicationContext, R.anim.slide_up)
            PlayerAvatarImageView.startAnimation(aniSlideup)
            Toast.makeText(this, player.spell[0].name, Toast.LENGTH_SHORT).show()
            monster.life -= player.spell[0].damage
            if (monster.life < 0) {
                monster.life = 0.0
            }
            enemyLifeTextView.text = monster.life.toString()
            if (player.life < 100) {
                player.life += player.spell[0].heal
                if (player.life > 100) {
                    player.life = 100
                }
            }
            PlayerLifeTextView.text = player.life.toString()
            if (monster.life <= 0.0) {
                deathMonster(player)
            } else {
                monsterDamage(player)
            }

        }

        PlayerSpell2ImageView.setOnClickListener {
            if (SystemClock.elapsedRealtime() - mLastClickTime < 4000) {
                return@setOnClickListener
            }
            mLastClickTime = SystemClock.elapsedRealtime()
            val aniSlideup = AnimationUtils.loadAnimation(applicationContext, R.anim.slide_up)
            PlayerAvatarImageView.startAnimation(aniSlideup)
            Toast.makeText(this, player.spell[1].name, Toast.LENGTH_SHORT).show()
            monster.life -= player.spell[1].damage
            enemyLifeTextView.text = monster.life.toString()
            if (player.life < 100) {
                player.life += player.spell[1].heal
                if (player.life > 100) {
                    player.life = 100
                }
            }
            PlayerLifeTextView.text = player.life.toString()
            if (monster.life <= 0) {
                deathMonster(player)
            } else {
                monsterDamage(player)
            }
        }


    }

    fun monsterDamage(player: Player) {
        Handler().postDelayed({
            val aniSlide = AnimationUtils.loadAnimation(applicationContext, R.anim.slide_down)
            enemyAvatarImageView.startAnimation(aniSlide)
            var critDamage: Double = monster.damage
            val rand = (1..100).random()
            if (Myapp.player.life <= 0.0) {
                deathPlayer()
            }

            if (rand < monster.critChance) {
                critDamage *= 1.5
                Toast.makeText(this, "Il fait un coup critique", Toast.LENGTH_SHORT).show()
            }

            player.life = player.life - (critDamage - (critDamage * (player.equipments.getArmor() / 100))).toInt()
            PlayerLifeTextView.text = player.life.toString()

            if (player.life <= 0) {
                deathPlayer()
            }
        }, 2500)
    }

    fun createWeapon(): Items {
        var item: Items
        item = Weapon((1..3).random(),"Epee de feu pas du boss", 50, (10..30).random())
        Handler().postDelayed({
            Toast.makeText(this, "vous remportez ${item.name}", Toast.LENGTH_SHORT).show()
        }, 500)
        return item
    }

    fun createChestplate(): Items {
        val item = Armor((1..3).random(),"Plastron de feu pas du boss", 20, (20..50).random(), ArmorType.chestplate)
        Handler().postDelayed({
            Toast.makeText(this, "vous remportez ${item.name}", Toast.LENGTH_SHORT).show()
        }, 500)

        return item
    }

    fun createBoots(): Items {
        val item = Armor((1..3).random(),"Bottes de feu pas du boss", 20, (20..50).random(), ArmorType.boots)
        Handler().postDelayed({
            Toast.makeText(this, "vous remportez ${item.name}", Toast.LENGTH_SHORT).show()
        }, 500)

        return item
    }


    fun createGloves(): Items {
        val item = Armor((1..3).random(),"Gants de feu pas du boss", 20, (20..50).random(), ArmorType.gloves)
        Handler().postDelayed({
            Toast.makeText(this, "vous remportez ${item.name}", Toast.LENGTH_SHORT).show()
        }, 500)

        return item
    }


    fun createHelmet(): Items {
        val item = Armor((1..3).random(),"Casque de feu pas du boss", 20, (20..50).random(), ArmorType.helmet)
        Handler().postDelayed({
            Toast.makeText(this, "vous remportez ${item.name}", Toast.LENGTH_SHORT).show()
        }, 500)

        return item
    }


    fun deathPlayer() {
        Toast.makeText(this, "vous etes morts",Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun deathMonster(player: Player) {
        Handler().postDelayed({
            player.gold += 50
            val rand = (1..5).random()

            when (rand) {
                1 -> player.items.add(createWeapon())
                2 -> player.items.add(createGloves())
                3 -> player.items.add(createHelmet())
                4 -> player.items.add(createChestplate())
                5 -> player.items.add(createBoots())
            }
            finish()
        }, 1500)
    }

}
