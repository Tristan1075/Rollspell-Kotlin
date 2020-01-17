package com.example.rollspellkotlin

import android.app.Application
import com.example.rollspellkotlin.Models.*

class Myapp : Application() {
    companion object {

        lateinit var player :Player


        fun createPlayer(
            name: String,
            weapon: Weapon,
            life: Double,
            armor: Armor,
            gold: Int,
            spell: ArrayList<Spell>,
            items: ArrayList<Items>
        ){
             player =
                Player(name, weapon, life, armor, gold, spell,items)
        }


    }
}
